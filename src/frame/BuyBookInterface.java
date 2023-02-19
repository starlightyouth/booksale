package frame;

import dao.BookDao;
import dao.OrderDao;
import model.Book;
import model.Order;
import util.Connect;
import util.StringNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 买书主界面
 */
public class BuyBookInterface extends JFrame {
    Map<Double, String> Map_discount = new HashMap<>() {
        /**
         *
         */

        {

            put(1.0, "无折扣");
            put(0.9, "九折");
            put(0.8, "八折");
            put(0.7, "七折");
            put(0.6, "六折");
            put(0.5, "五折");
            put(0.4, "四折");
            put(0.3, "三折");
            put(0.2, "二折");
            put(0.1, "一折");
        }
    };
    private JPanel contentPane;
    private final JLabel label = new JLabel("");
    private final JLabel LabelId = new JLabel("请输入待购买图书的编号：");
    private final JLabel LabelId_1 = new JLabel("图书编号：");
    private final JTextField textField = new JTextField();
    private final JButton ButtonEnter = new JButton("载入");
    private final JLabel LabelMessage = new JLabel("请确认待购买图书信息：");
    private final JLabel LabelName = new JLabel("图书名：");
    private final JTextField textField_1 = new JTextField();
    private final JLabel LabelWriter = new JLabel("图书作者：");
    private final JTextField textField_2 = new JTextField();
    private final JLabel LabelPolish = new JLabel("出版社：");
    private final JTextField textField_3 = new JTextField();
    private final JLabel LabelPrice = new JLabel("图书价格：");
    private final JTextField textField_4 = new JTextField();
    private final JButton ButtonBuy = new JButton("购买");
    private final JButton ButtonDel = new JButton("重置");
    private final JLabel LabelDocunt = new JLabel("图书折扣：");
    private final JTextField textField_5 = new JTextField();
    private final JLabel label_1 = new JLabel("");
    private final JLabel LabelNumber = new JLabel("请输入购买书籍数量：");
    private final JTextField textField_6 = new JTextField();
    private final JLabel LabelPriceTotal = new JLabel("价格总计：");
    private final JLabel Label_2 = new JLabel("NaN");
    private Connect conutil = new Connect();
    private BookDao bookDao = new BookDao();
    private OrderDao orderDao = new OrderDao();
    Double temp_discount;
    private final JButton ButtonConfim = new JButton("确认");
    private final JLabel LabelNotes = new JLabel("备注：");
    private final JTextField textField_7 = new JTextField();

    /**
     * Create the frame.
     */
    public BuyBookInterface() {

        this.textField_6.setEditable(false);
        this.textField_6.setText("1");
        this.textField_6.setBounds(251, 371, 90, 30);
        this.textField_6.setColumns(10);
        setTitle("购买图书");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 518);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.label.setBounds(19, 16, 395, 330);
        contentPane.add(this.label);
        this.LabelId.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelId.setBounds(35, 28, 260, 29);
        setResizable(false);
        contentPane.add(this.LabelId);
        this.LabelId_1.setBounds(35, 69, 60, 18);

        contentPane.add(this.LabelId_1);
        this.textField.setColumns(10);
        this.textField.setBounds(107, 63, 188, 30);

        contentPane.add(this.textField);
        this.ButtonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loadbook();
            }
        });
        this.ButtonEnter.setBounds(307, 63, 90, 30);

        contentPane.add(this.ButtonEnter);
        this.LabelMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelMessage.setBounds(35, 99, 260, 29);

        contentPane.add(this.LabelMessage);
        this.LabelName.setBounds(35, 140, 48, 18);

        contentPane.add(this.LabelName);
        this.textField_1.setEditable(false);
        this.textField_1.setColumns(10);
        this.textField_1.setBounds(91, 134, 250, 30);

        contentPane.add(this.textField_1);
        this.LabelWriter.setBounds(35, 182, 60, 18);

        contentPane.add(this.LabelWriter);
        this.textField_2.setEditable(false);
        this.textField_2.setColumns(10);
        this.textField_2.setBounds(91, 176, 250, 30);

        contentPane.add(this.textField_2);
        this.LabelPolish.setBounds(35, 222, 48, 18);

        contentPane.add(this.LabelPolish);
        this.textField_3.setEditable(false);
        this.textField_3.setColumns(10);
        this.textField_3.setBounds(91, 216, 250, 30);

        contentPane.add(this.textField_3);
        this.LabelPrice.setBounds(35, 262, 60, 18);

        contentPane.add(this.LabelPrice);
        this.textField_4.setEditable(false);
        this.textField_4.setColumns(10);
        this.textField_4.setBounds(91, 258, 250, 30);

        contentPane.add(this.textField_4);
        this.ButtonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyBook();
            }
        });
        this.ButtonBuy.setBounds(91, 443, 90, 30);
        this.ButtonBuy.setEnabled(false);// 未载入书籍的状态下不可购买
        contentPane.add(this.ButtonBuy);
        this.ButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setEditable(true);// 重置使得书籍编号可以重新输入
                ButtonBuy.setEnabled(false);// 使购买按钮不可用
                delActiontxt();
            }
        });
        this.ButtonDel.setBounds(251, 443, 90, 30);

        contentPane.add(this.ButtonDel);
        this.LabelDocunt.setBounds(35, 302, 60, 18);

        contentPane.add(this.LabelDocunt);
        this.textField_5.setEditable(false);
        this.textField_5.setColumns(10);
        this.textField_5.setBounds(91, 300, 250, 30);

        contentPane.add(this.textField_5);
        this.label_1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.label_1.setBounds(19, 358, 395, 115);

        contentPane.add(this.label_1);
        this.LabelNumber.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelNumber.setBounds(35, 369, 260, 29);

        contentPane.add(this.LabelNumber);

        contentPane.add(this.textField_6);
        this.LabelPriceTotal.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelPriceTotal.setBounds(251, 410, 260, 29);

        contentPane.add(this.LabelPriceTotal);
        this.Label_2.setFont(new Font("SansSerif", Font.BOLD, 16));
        this.Label_2.setForeground(Color.RED);
        this.Label_2.setBounds(343, 413, 205, 29);

        contentPane.add(this.Label_2);
        this.ButtonConfim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Updateprice();
            }
        });
        this.ButtonConfim.setBounds(343, 371, 54, 30);

        contentPane.add(this.ButtonConfim);
        this.LabelNotes.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelNotes.setBounds(35, 410, 60, 29);

        contentPane.add(this.LabelNotes);
        this.textField_7.setText("无备注");
        this.textField_7.setColumns(10);
        this.textField_7.setBounds(91, 410, 148, 30);

        contentPane.add(this.textField_7);
        setLocationRelativeTo(null);

    }

    /**
     * 载入待购买书籍
     */
    private void Loadbook() {
        Book book = new Book();
        if (StringNull.isEmpty(this.textField.getText())) {
            JOptionPane.showMessageDialog(null, "图书编号不能为空！");
            delActiontxt();
            return;
        }
        book.setBook_id(Integer.parseInt(this.textField.getText()));
        Connection con = null;
        try {
            con = conutil.loding();
            ResultSet rs = bookDao.query2(con, book);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "未找到该书籍!");
                delActiontxt();
            } else {
                textField.setEditable(false);// 暂时设置无法修改书籍编号。
                ButtonBuy.setEnabled(true);// 使购买按钮可用
                textField_6.setEditable(true);// 使购买数量按钮可用
                textField_1.setText(rs.getString("book_name"));
                textField_2.setText(rs.getString("book_writer"));
                textField_3.setText(rs.getString("book_publish"));
                textField_4.setText(rs.getString("book_price"));
                textField_5.setText(Map_discount.get(Double.parseDouble(rs.getString("book_discount"))));
                temp_discount = rs.getDouble("book_discount");
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

    private void Updateprice() {
        int num = Integer.parseInt(textField_6.getText());
        Double price = Double.parseDouble(textField_4.getText());
        Double discount = temp_discount;
        Double totalprice = price * discount * num;
        Label_2.setText(String.format("%.4f", totalprice));
    }

    private void BuyBook() {
        int num = Integer.parseInt(textField_6.getText());
        Double price = Double.parseDouble(textField_4.getText());
        Double discount = temp_discount;
        Double orderprice = price * discount;
        Double totalprice = Double.parseDouble(Label_2.getText());
        String bookId = this.textField.getText();
        String remark = this.textField_7.getText();
        int readerid = ReaderMainInterface.readerid;
        Connection con = null;
        try {
            con = conutil.loding();
            Book book = new Book(Integer.parseInt(bookId));
            Order order = new Order(orderprice, totalprice, num, remark, Integer.parseInt(bookId), readerid);

            ResultSet rs = bookDao.query2(con, book);
            if (rs.next()) {
                int inventory = rs.getInt("book_amount");
                int newinventory = inventory - num;
                if (newinventory >= 0) {
                    book.setBook_amount(newinventory);
                    bookDao.inventory(con, book);
                    orderDao.add(con, order);
                    JOptionPane.showMessageDialog(null, "购买成功!订单添加成功，可在我的订单中查看。");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "购买失败，该图书库存不足！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "购买失败！未知错误！");
            return;
        } finally {
            try {
                conutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 清空
    private void delActiontxt() {
        this.textField.setText("");
        this.textField_1.setText("");
        this.textField_2.setText("");
        this.textField_3.setText("");
        this.textField_4.setText("");
        this.textField_5.setText("");
        this.textField_6.setText("1");
    }
}