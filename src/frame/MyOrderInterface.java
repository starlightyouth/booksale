package frame;

import dao.OrderDao;
import util.AutoFitTableColums;
import util.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * 我的订单界面
 */
public class MyOrderInterface extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 3868487575714169592L;
    private JPanel contentPane;
    private final JLabel label = new JLabel("");
    private final JLabel LabelList = new JLabel("  您的订单列表如下：");
    private final JScrollPane scrollPane = new JScrollPane();
    private final JTable orderTable = new JTable();
    private Connect conutil = new Connect();
    private OrderDao orderDao = new OrderDao();

    /**
     * Create the frame.
     */
    public MyOrderInterface() {
        setResizable(false);
        setTitle("我的订单");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 570);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.label.setBounds(6, 15, 427, 29);

        contentPane.add(this.label);
        this.LabelList.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelList.setBounds(6, 15, 286, 29);

        contentPane.add(this.LabelList);
        this.scrollPane.setBounds(0, 47, 433, 478);

        contentPane.add(this.scrollPane);
        String[] columnNames = {"订单编号", "单价", "总价", "数量", "图书编号", "用户编号", "备注",};
        orderTable.setFillsViewportHeight(true);
        orderTable.setModel(new DefaultTableModel(new Object[][]{}, columnNames) {
            private static final long serialVersionUID = 2270348433879980994L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        this.scrollPane.setViewportView(orderTable);
        Precisequery();// 精确检索属于order_reader_id的订单
        AutoFitTableColums.FitTableColumns(orderTable);// 自适应列宽

    }

    private void Precisequery() {
        DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
        dtm.setRowCount(0);
        int readerId = ReaderMainInterface.readerid;
        Connection con = null;
        try {
            con = conutil.loding();
            ResultSet rs = orderDao.query2(con, readerId);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("order_id"));
                v.add(rs.getString("order_price"));
                v.add(rs.getString("order_total"));
                v.add(rs.getString("order_amount"));
                v.add(rs.getString("order_book_id"));
                v.add(rs.getString("order_reader_id"));
                v.add(rs.getString("order_remark"));
                dtm.addRow(v);
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
}