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
 * 删除图书界面
 */
public class DeleteBookInterface extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -3112379638608650243L;
    private JPanel contentPane;
    private final JLabel LabelId = new JLabel("图书编号：");
    private final JLabel LabelId_1 = new JLabel("请输入待删除图书的图书编号：");
    private final JTextField textField = new JTextField();
    private final JButton ButtonConfim = new JButton("确认删除");
    private final JLabel LabelWarn = new JLabel("警告：删除图书会清空该图书的所有库存！");
    private BookDao bookDao = new BookDao();
    private Connect conutil = new Connect();

    /**
     * Create the frame.
     */
    public DeleteBookInterface() {
        this.textField.setBounds(118, 46, 122, 30);
        this.textField.setColumns(10);
        setResizable(false);
        setTitle("图书删除");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 255);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        this.LabelId.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelId.setBounds(6, 44, 100, 29);

        contentPane.add(this.LabelId);
        this.LabelId_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.LabelId_1.setBounds(6, 18, 224, 23);

        contentPane.add(this.LabelId_1);

        contentPane.add(this.textField);
        this.ButtonConfim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        this.ButtonConfim.setBounds(105, 142, 151, 60);

        contentPane.add(this.ButtonConfim);
        this.LabelWarn.setFont(new Font("SansSerif", Font.BOLD, 18));
        this.LabelWarn.setBounds(7, 96, 353, 26);

        contentPane.add(this.LabelWarn);
    }

    protected void deleteBook() {
        String bookId = this.textField.getText();

        if (StringNull.isEmpty(bookId)) {
            JOptionPane.showMessageDialog(null, "图书编号不能为空！");
            return;
        }

        Connection con = null;
        try {
            con = conutil.loding();
            Book book = new Book(Integer.parseInt(bookId));
            ResultSet rs = bookDao.query2(con, book);
            if (rs.next()) {
                bookDao.delete(con, Integer.parseInt(bookId));
                JOptionPane.showMessageDialog(null, "删除成功!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "删除失败！未找到该编号的书籍");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "删除失败！未知错误！");
            return;
        } finally {
            try {
                conutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
