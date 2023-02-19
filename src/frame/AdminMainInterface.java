package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 管理主界面
 */
public class AdminMainInterface extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 4607476557963651822L;
    /**
     *
     */
    private JPanel contentPane;
    private final JLabel label = new JLabel("");
    private final JLabel Label1 = new JLabel("  图书管理：");
    private final JButton Button = new JButton("图书入库");
    private final JButton Button1 = new JButton("图书修改");
    private final JButton Button2 = new JButton("图书删除");
    private final JButton Button3 = new JButton("图书查询");
    private final JButton Button4 = new JButton("图书折扣");
    private final JButton Button5 = new JButton("库存修改");
    private final JLabel labelkong = new JLabel("");
    private final JLabel Label2 = new JLabel("  用户管理：");
    private final JButton Button_1 = new JButton("用户查询");
    private final JButton Button_2 = new JButton("用户添加");
    private final JButton Button_3 = new JButton("用户删除");
    private String adminname;
    private final JLabel Label_1 = new JLabel("尊敬的图书馆管理员：");
    private final JLabel Label_2 = new JLabel("");
    private final JLabel Label_3 = new JLabel("您目前处于管理员主界面，点击相应按钮即可进入操作界面。");
    private final JButton Button6 = new JButton("检查订单");

    /**
     * Create the frame.
     */
    public AdminMainInterface() {
        adminname = EnterInterface.adminname;
        setResizable(false);
        setTitle("管理员主界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 455, 570);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        label.setBounds(6, 6, 422, 202);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        contentPane.add(this.label);
        this.Label1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.Label1.setBounds(6, 6, 88, 23);

        contentPane.add(this.Label1);
        this.Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookInterface addBookInterface = new AddBookInterface();
                addBookInterface.setVisible(true);
            }
        });
        this.Button.setBounds(15, 36, 125, 67);

        contentPane.add(this.Button);
        this.Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyBookInterface modifyBookInterface = new ModifyBookInterface();
                modifyBookInterface.setVisible(true);
            }
        });
        this.Button1.setBounds(150, 36, 125, 67);

        contentPane.add(this.Button1);
        this.Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBookInterface deleteReaderInterface = new DeleteBookInterface();
                deleteReaderInterface.setVisible(true);
            }
        });
        this.Button2.setBounds(291, 36, 125, 35);

        contentPane.add(this.Button2);
        this.Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBookInterface queryBookInterface = new QueryBookInterface();
                queryBookInterface.setVisible(true);
            }
        });
        this.Button3.setBounds(15, 115, 125, 67);

        contentPane.add(this.Button3);
        this.Button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisdocuntInterface disdocuntInterface = new DisdocuntInterface();
                disdocuntInterface.setVisible(true);
            }
        });
        this.Button4.setBounds(291, 132, 125, 45);

        contentPane.add(this.Button4);
        this.Button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryModificationInterface inventoryModificationInterface = new InventoryModificationInterface();
                inventoryModificationInterface.setVisible(true);
            }
        });
        this.Button5.setBounds(291, 78, 125, 45);

        contentPane.add(this.Button5);
        this.labelkong.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.labelkong.setBounds(6, 220, 422, 133);

        contentPane.add(this.labelkong);
        this.Label2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.Label2.setBounds(6, 238, 88, 23);

        contentPane.add(this.Label2);
        this.Button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryReaderInterface queryReaderInterface = new QueryReaderInterface();
                queryReaderInterface.setVisible(true);
            }
        });
        this.Button_1.setBounds(15, 273, 125, 67);

        contentPane.add(this.Button_1);
        this.Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddReaderInterface addReaderInterface = new AddReaderInterface();
                addReaderInterface.setVisible(true);
            }
        });
        this.Button_2.setBounds(150, 273, 125, 67);

        contentPane.add(this.Button_2);
        this.Button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteReaderInterface deleteReaderInterface = new DeleteReaderInterface();
                deleteReaderInterface.setVisible(true);
            }
        });
        this.Button_3.setBounds(291, 273, 125, 67);

        contentPane.add(this.Button_3);
        this.Label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.Label_1.setBounds(15, 365, 168, 23);

        contentPane.add(this.Label_1);
        this.Label_2.setHorizontalAlignment(SwingConstants.CENTER);
        this.Label_2.setFont(new Font("SansSerif", Font.BOLD, 48));
        this.Label_2.setBounds(25, 389, 391, 94);
        Label_2.setText(adminname);
        contentPane.add(this.Label_2);
        this.Label_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
        this.Label_3.setBounds(6, 473, 427, 52);

        contentPane.add(this.Label_3);
        this.Button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckOrderInterface checkOrderInterface = new CheckOrderInterface();
                checkOrderInterface.setVisible(true);
            }
        });
        this.Button6.setBounds(150, 115, 125, 67);

        contentPane.add(this.Button6);
    }
}
