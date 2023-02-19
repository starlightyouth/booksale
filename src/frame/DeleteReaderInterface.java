package frame;

import dao.ReaderDao;
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
 * 删除读者界面
 */
public class DeleteReaderInterface extends JFrame {

    private static final long serialVersionUID = -1878106319697766203L;
    private JPanel contentPane;
    private final JLabel LabelId = new JLabel("用户编号：");
    private final JLabel LabelId_1 = new JLabel("请输入待注销的用户编号：");
    private final JTextField textField = new JTextField();
    private final JButton ButtonConfim = new JButton("确认注销");
    private final JLabel LabelWarn = new JLabel("警告：此操作不可逆！");
    private Connect conutil = new Connect();
    private ReaderDao readerDao = new ReaderDao();

    /**
     * Create the frame.
     */
    public DeleteReaderInterface() {
        this.textField.setBounds(118, 46, 122, 30);
        this.textField.setColumns(10);
        setResizable(false);
        setTitle("用户注销");
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
                deleteReader();
            }
        });
        this.ButtonConfim.setBounds(105, 142, 151, 60);

        contentPane.add(this.ButtonConfim);
        this.LabelWarn.setFont(new Font("SansSerif", Font.BOLD, 18));
        this.LabelWarn.setBounds(7, 96, 353, 26);

        contentPane.add(this.LabelWarn);
    }

    protected void deleteReader() {
        String ReaderId = this.textField.getText();
        if (StringNull.isEmpty(ReaderId)) {
            JOptionPane.showMessageDialog(null, "用户编号不能为空！");
            return;
        }
        Connection con = null;
        try {
            con = conutil.loding();
            ResultSet rs = readerDao.query(con, Integer.parseInt(ReaderId));
            if (rs.next()) {
                readerDao.delete(con, Integer.parseInt(ReaderId));
                JOptionPane.showMessageDialog(null, "删除成功!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "删除失败！未找到该编号用户！");
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