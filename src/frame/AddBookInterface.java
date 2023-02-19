package frame;

import dao.BookDao;
import model.Book;
import util.Connect;
import util.StringNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * 添加图书界面
 */
public class AddBookInterface extends JFrame{

    private static final long serialVersionUID = -7230175691567354083L;
    private JPanel contentPane;
    private final JLabel LabelId = new JLabel("图书编号：");
    private final JTextField textField = new JTextField();
    private final JLabel LabelName = new JLabel("图书名：");
    private final JTextField textField_1 = new JTextField();
    private final JLabel LabelWriter = new JLabel("图书作者：");
    private final JTextField textField_2 = new JTextField();
    private final JLabel LabelPoblish = new JLabel("出版社：");
    private final JTextField textField_3 = new JTextField();
    private final JLabel LabelModification = new JLabel("图书库存：");
    private final JTextField textField_4 = new JTextField();
    private final JLabel LabelPrice = new JLabel("图书价格：");
    private final JTextField textField_5 = new JTextField();
    private final JButton ButtonAdd = new JButton("添加");
    private final JButton ButtonQingKong = new JButton("清空");
    private final JLabel LabelMessage = new JLabel("请输入要入库的图书信息：");
    private final JLabel label = new JLabel("");
    private Connect conutil = new Connect();
    private BookDao bookDao = new BookDao();

    /**
     * Create the frame.
     */
    public AddBookInterface() {
        setResizable(false);
        this.textField.setBounds(113, 78, 250, 30);
        this.textField.setColumns(10);
        setTitle("图书入库");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label.setBounds(19, 16, 395, 418);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        contentPane.add(this.label);

        this.LabelId.setBounds(41, 84, 60, 18);
        contentPane.add(this.LabelId);

        contentPane.add(this.textField);
        this.LabelName.setBounds(41, 140, 48, 18);

        contentPane.add(this.LabelName);
        this.textField_1.setColumns(10);
        this.textField_1.setBounds(113, 134, 250, 30);

        contentPane.add(this.textField_1);
        this.LabelWriter.setBounds(41, 196, 60, 18);

        contentPane.add(this.LabelWriter);
        this.textField_2.setColumns(10);
        this.textField_2.setBounds(113, 190, 250, 30);

        contentPane.add(this.textField_2);
        this.LabelPoblish.setBounds(41, 252, 48, 18);

        contentPane.add(this.LabelPoblish);
        this.textField_3.setColumns(10);
        this.textField_3.setBounds(113, 246, 250, 30);

        contentPane.add(this.textField_3);
        this.LabelModification.setBounds(41, 308, 60, 18);

        contentPane.add(this.LabelModification);
        this.textField_4.setColumns(10);
        this.textField_4.setBounds(113, 302, 250, 30);

        contentPane.add(this.textField_4);
        this.LabelPrice.setBounds(41, 364, 60, 18);

        contentPane.add(this.LabelPrice);
        this.textField_5.setColumns(10);
        this.textField_5.setBounds(113, 358, 250, 30);

        contentPane.add(this.textField_5);
        this.ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        this.ButtonAdd.setBounds(75, 463, 90, 30);

        contentPane.add(this.ButtonAdd);
        this.ButtonQingKong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delActiontxt();
            }
        });
        this.ButtonQingKong.setBounds(277, 463, 90, 30);

        contentPane.add(this.ButtonQingKong);
        this.LabelMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelMessage.setBounds(38, 37, 240, 29);

        contentPane.add(this.LabelMessage);
        setLocationRelativeTo(null);

    }

    // 图书入库
    protected void addBook() {
        String bookId = this.textField.getText();
        String bookName = this.textField_1.getText();
        String bookPublish = this.textField_2.getText();
        String bookWriter = this.textField_3.getText();
        String bookAmount = this.textField_4.getText();
        String bookPrice = this.textField_5.getText();
        if (StringNull.isEmpty(bookId)) {
            JOptionPane.showMessageDialog(null, "图书编号不能为空！");
            return;
        }
        if (StringNull.isEmpty(bookName)) {
            JOptionPane.showMessageDialog(null, "图书名不能为空！");
            return;
        }
        if (StringNull.isEmpty(bookPublish)) {
            JOptionPane.showMessageDialog(null, "图书出版社不能为空！");
            return;
        }
        if (StringNull.isEmpty(bookWriter)) {
            JOptionPane.showMessageDialog(null, "图书作者不能为空！");
            return;
        }
        if (StringNull.isEmpty(bookAmount)) {
            JOptionPane.showMessageDialog(null, "图书库存不能为空！");
            return;
        }
        if (StringNull.isEmpty(bookPrice)) {
            JOptionPane.showMessageDialog(null, "图书价格不能为空！");
            return;
        }
        Connection con = null;
        try {
            Book book = new Book(Integer.parseInt(bookId), bookName, bookWriter, bookPublish,
                    Integer.parseInt(bookAmount), Double.parseDouble(bookPrice));
            con = conutil.loding();
            ResultSet rs = bookDao.query2(con, book);// 先检查此书是否已添加

            if (rs.next()) { // 此书已添加
                JOptionPane.showMessageDialog(null, "添加失败，此书已添加！");
                return;
            } else {// 此书未添加
                bookDao.add(con, book);
                JOptionPane.showMessageDialog(null, "添加成功！");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 清空输入框
    private void delActiontxt() {
        this.textField.setText("");
        this.textField_1.setText("");
        this.textField_2.setText("");
        this.textField_3.setText("");
        this.textField_4.setText("");
        this.textField_5.setText("");
    }
}
