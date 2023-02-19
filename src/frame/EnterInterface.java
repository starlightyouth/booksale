package frame;

import dao.AdminDao;
import dao.ReaderDao;
import model.Admin;
import model.Reader;
import util.Connect;
import util.StringNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * 登录进入界面
 */
public class EnterInterface extends JFrame {

    //private static final long serialVersionUID = -2873897152598765887L;
    private final JLabel title = new JLabel("图书销售管理系统");
    private final JRadioButton LoginRadioButton = new JRadioButton("用户登录");
    private final JRadioButton LoginRadioButton1 = new JRadioButton("管理员登录");
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JLabel UserLabel = new JLabel("用户名：");
    private final JLabel PasswordLabel = new JLabel("密  码：");
    private final JTextField textField = new JTextField();
    private final JPasswordField textField_1 = new JPasswordField();
    private final JButton LoginButton = new JButton("登录");
    private final JButton RegisterButton = new JButton("注册");
    private int action = 0;
    private Connect conutil = new Connect();
    private AdminDao adminDao = new AdminDao();
    private ReaderDao readerDao = new ReaderDao();
    private ImageIcon img = new ImageIcon("imgs/enter.jpg");
    private JLabel bg = new JLabel(img); // 背景图片载入到JLabel
    public static String adminname;
    public static String readername;
    public static int readerid;


    /**
     * Create the frame.
     */
    public EnterInterface() {

        this.textField.setBounds(94, 115, 216, 30);
        this.textField.setColumns(10);
        setTitle("登录界面");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 350);
        getContentPane().setLayout(null);
        this.bg.setBounds(0, 0, 400, 350);
        this.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
        ((JComponent) this.getContentPane()).setOpaque(false); // 设置透明
        setLocationRelativeTo(null);
        this.title.setForeground(Color.ORANGE);
        this.title.setFont(new Font("微软雅黑", Font.BOLD, 27));
        this.title.setToolTipText("");
        this.title.setBounds(84, 6, 216, 37);

        getContentPane().add(this.title);
        this.LoginRadioButton.setForeground(Color.ORANGE);
        this.LoginRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = 1;
            }
        });
        this.LoginRadioButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.LoginRadioButton.setBounds(81, 55, 80, 20);

        getContentPane().add(this.LoginRadioButton);
        this.LoginRadioButton1.setForeground(Color.ORANGE);
        this.LoginRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = 2;
            }
        });
        this.LoginRadioButton1.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.LoginRadioButton1.setBounds(207, 55, 94, 20);
        buttonGroup.add(LoginRadioButton);

        getContentPane().add(this.LoginRadioButton1);
        buttonGroup.add(LoginRadioButton1);
        this.UserLabel.setForeground(Color.ORANGE);
        this.UserLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.UserLabel.setBounds(18, 112, 83, 29);

        getContentPane().add(this.UserLabel);
        this.PasswordLabel.setForeground(Color.ORANGE);
        this.PasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        this.PasswordLabel.setBounds(18, 161, 72, 28);

        getContentPane().add(this.PasswordLabel);

        getContentPane().add(this.textField);
        this.textField_1.setColumns(10);
        this.textField_1.setBounds(94, 164, 216, 30);

        getContentPane().add(this.textField_1);
        this.LoginButton.addActionListener(new ActionListener() {// 登录按钮事件监听器。
            @Override
            public void actionPerformed(ActionEvent e) {
                if (0 == action)
                    JOptionPane.showMessageDialog(null, "请选择登录方式！");
                if (1 == action) {
                    int toRmif = userLogin(e);
                    if (1 == toRmif) {
                        ReaderMainInterface Rmif = new ReaderMainInterface();
                        Rmif.setVisible(true);
                    }
                }
                if (2 == action) {
                    int toAmif = adminLogin(e);
                    if (1 == toAmif) {
                        AdminMainInterface Amif = new AdminMainInterface();
                        Amif.setVisible(true);
                    }
                }
            }
        });

        this.LoginButton.setBounds(220, 235, 90, 30);
        this.LoginButton.setForeground(Color.BLACK);
        getContentPane().add(this.LoginButton);

        this.RegisterButton.addActionListener(new ActionListener() { // 注册按钮事件监听器。
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterInterface ri = new RegisterInterface();
                ri.setVisible(true);
            }
        });
        this.RegisterButton.setBounds(94, 235, 90, 30);
        this.RegisterButton.setForeground(Color.BLACK);
        getContentPane().add(this.RegisterButton);

    }

    /*
     * 管理员登录事件处理
     */
    private int adminLogin(ActionEvent e) {
        String userName = this.textField.getText();
        String password = new String(this.textField_1.getPassword());// 获取密码
        // 提示框
        if (StringNull.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "管理员名不能为空！");
            return 0;
        }
        if (StringNull.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return 0;
        }
        Admin admin = new Admin(userName, password);
        Connection con = null;
        try {
            con = conutil.loding();// 连接数据库
            Admin curreatAdmin = adminDao.login(con, admin);// 调用管理员数据处理类进行登录验证。

            if (curreatAdmin != null) {
                dispose();// 关闭登录窗口
                // 提示框
                JOptionPane.showMessageDialog(null, "管理员登陆成功！");
                adminname = admin.getAdmin_name();
                return 1;
            } else {
                JOptionPane.showMessageDialog(null, "管理员名或者密码错误！");
                return 0;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        } finally {
            try {
                conutil.closeCon(con);// 关闭数据库连接
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /*
     * 用户登录事件处理
     */
    private int userLogin(ActionEvent e) {
        String userName1 = this.textField.getText();
        String password1 = new String(this.textField_1.getPassword());// 获取密码
        if (StringNull.isEmpty(userName1)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
            return 0;
        }
        if (StringNull.isEmpty(password1)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return 0;
        }
        Reader reader = new Reader(userName1, password1);
        Connection con = null;
        try {
            con = conutil.loding();// 连接数据库
            Reader curreatReader = readerDao.login(con, reader);

            if (curreatReader != null) {
                dispose();// 关闭登录窗口
                JOptionPane.showMessageDialog(null, "登陆成功！");
                readername = curreatReader.getReader_name();
                readerid = curreatReader.getReader_id();
                return 1;
            } else {

                JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
                return 0;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        } finally {
            try {
                conutil.closeCon(con);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
