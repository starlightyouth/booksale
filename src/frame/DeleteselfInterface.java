package frame;

import dao.ReaderDao;
import util.Connect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * 删除账号界面
 */
public class DeleteselfInterface extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -1878106319697766203L;
    private JPanel contentPane;
    private final JLabel LabelMessage = new JLabel("尊敬的用户您好，您正在执行删除账号操作");
    private final JButton ButtonConfim = new JButton("确认删除");
    private final JLabel LabelWarn = new JLabel("警告：此操作不可逆！");
    private Connect conutil = new Connect();
    private ReaderDao readerDao = new ReaderDao();
    private final JLabel LabelMessage_1 = new JLabel("请确认您的用户编号：");
    private final JLabel Label = new JLabel("-1");

    public DeleteselfInterface() {
        setResizable(false);
        setTitle("删除账号");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 255);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        this.LabelMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelMessage.setBounds(7, 6, 380, 29);

        contentPane.add(this.LabelMessage);
        this.ButtonConfim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelf();
            }
        });
        this.ButtonConfim.setBounds(105, 142, 151, 60);

        contentPane.add(this.ButtonConfim);
        this.LabelWarn.setForeground(Color.RED);
        this.LabelWarn.setFont(new Font("SansSerif", Font.BOLD, 18));
        this.LabelWarn.setBounds(7, 96, 353, 26);

        contentPane.add(this.LabelWarn);
        this.LabelMessage_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelMessage_1.setBounds(7, 47, 200, 29);

        contentPane.add(this.LabelMessage_1);
        this.Label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.Label.setBounds(219, 47, 200, 29);

        contentPane.add(this.Label);
        Label.setText(String.valueOf(ReaderMainInterface.readerid));
    }

    protected void deleteSelf() {
        String ReaderId = this.Label.getText();
        Connection con = null;
        try {
            con = conutil.loding();
            ResultSet rs = readerDao.query(con, Integer.parseInt(ReaderId));
            if (rs.next()) {
                readerDao.delete(con, Integer.parseInt(ReaderId));
                JOptionPane.showMessageDialog(null, "删除账号成功，欢迎再次使用。");
                System.exit(0);// 完全退出整个程序
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