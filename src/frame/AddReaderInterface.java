package frame;

import dao.ReaderDao;
import model.Reader;
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
 * 添加读者界面
 */
public class AddReaderInterface extends JFrame {
    private static final long serialVersionUID = -7230175691567354083L;
    private JPanel contentPane;
    private final JLabel LabelId = new JLabel("用户编号：");
    private final JTextField textField = new JTextField();
    private final JLabel LabelName = new JLabel("用户名：");
    private final JTextField textField_1 = new JTextField();
    private final JLabel LabelPhone = new JLabel("用户手机号：");
    private final JTextField textField_2 = new JTextField();
    private final JLabel LabelPassword = new JLabel("用户密码：");
    private final JTextField textField_3 = new JTextField();

    private final JButton ButtonAdd = new JButton("添加");
    private final JButton ButtonDel = new JButton("清空");
    private final JLabel LabelMessage = new JLabel("请输入要添加的用户信息：");
    private final JLabel label = new JLabel("");
    private Connect conutil = new Connect();
    private ReaderDao readerDao = new ReaderDao();

    /**
     * Create the frame.
     */
    public AddReaderInterface() {
        setResizable(false);
        this.textField.setBounds(113, 78, 250, 30);
        this.textField.setColumns(10);
        setTitle("用户添加");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 455, 425);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label.setBounds(19, 16, 395, 289);
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
        this.LabelPhone.setBounds(41, 196, 72, 18);

        contentPane.add(this.LabelPhone);
        this.textField_2.setColumns(10);
        this.textField_2.setBounds(113, 190, 250, 30);

        contentPane.add(this.textField_2);
        this.LabelPassword.setBounds(41, 252, 60, 18);

        contentPane.add(this.LabelPassword);
        this.textField_3.setColumns(10);
        this.textField_3.setBounds(113, 246, 250, 30);

        contentPane.add(this.textField_3);
        this.ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReader();
            }
        });

        this.ButtonAdd.setBounds(71, 336, 90, 30);

        contentPane.add(this.ButtonAdd);
        this.ButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delActiontxt();
            }
        });
        this.ButtonDel.setBounds(273, 336, 90, 30);

        contentPane.add(this.ButtonDel);
        this.LabelMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.LabelMessage.setBounds(38, 37, 240, 29);

        contentPane.add(this.LabelMessage);
        setLocationRelativeTo(null);
    }

    protected void addReader() {
        String ReaderId = this.textField.getText();
        String ReaderName = this.textField_1.getText();
        String ReaderTEL = this.textField_2.getText();
        String Readerpass = this.textField_3.getText();
        if (StringNull.isEmpty(ReaderId)) {
            JOptionPane.showMessageDialog(null, "用户编号不能为空！");
            return;
        }
        if (StringNull.isEmpty(ReaderName)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
            return;
        }
        if (StringNull.isEmpty(ReaderTEL)) {
            JOptionPane.showMessageDialog(null, "用户手机号不能为空！");
            return;
        }
        if (StringNull.isEmpty(Readerpass)) {
            JOptionPane.showMessageDialog(null, "用户密码不能为空！");
            return;
        }
        Connection con = null;
        try {
            Reader reader = new Reader(Integer.parseInt(ReaderId), ReaderName, ReaderTEL, Readerpass);
            con = conutil.loding();
            ResultSet rs = readerDao.query2(con, reader);// 先检查此书是否已添加
            if (rs.next()) { // 此书已添加
                JOptionPane.showMessageDialog(null, "添加失败，此用户编号已存在！");
                return;
            } else {// 此书未添加
                readerDao.register(con, reader);
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

    private void delActiontxt() {
        this.textField.setText("");
        this.textField_1.setText("");
        this.textField_2.setText("");
        this.textField_3.setText("");
    }
}
