package frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 读者主界面
 */
public class ReaderMainInterface extends JFrame {


    private static final long serialVersionUID = 2817048601123480207L;
    private JPanel contentPane;
    public static String readername;
    public static int readerid;
    private final JLabel label = new JLabel("");
    private final JLabel LabelMessage = new JLabel("  用户操作：");
    private final JLabel LabelMessage_1 = new JLabel("尊敬的用户：");
    private final JLabel LabelMessage_2 = new JLabel("您目前处于用户主界面，点击相应按钮即可进入操作界面。");
    private final JLabel LabelNull = new JLabel((String) null);
    private final JButton ButtonQuery = new JButton("图书查询");
    private final JButton ButtonBuy = new JButton("图书购买");
    private final JButton ButtonDel = new JButton("删除账号");
    private final JButton ButtonMyOrder = new JButton("我的订单");

    /**
     * Create the frame.
     */
    public ReaderMainInterface() {

        setTitle("用户主界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 455, 417);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.label.setBounds(6, 6, 422, 194);
        setResizable(false);
        contentPane.add(this.label);
        this.LabelMessage.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.LabelMessage.setBounds(6, 6, 88, 23);

        contentPane.add(this.LabelMessage);
        this.LabelMessage_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.LabelMessage_1.setBounds(6, 212, 168, 23);

        contentPane.add(this.LabelMessage_1);
        this.LabelMessage_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
        this.LabelMessage_2.setBounds(6, 320, 427, 52);

        contentPane.add(this.LabelMessage_2);
        this.LabelNull.setHorizontalAlignment(SwingConstants.CENTER);
        this.LabelNull.setFont(new Font("SansSerif", Font.BOLD, 48));
        this.LabelNull.setBounds(6, 240, 422, 86);

        contentPane.add(this.LabelNull);
        this.ButtonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBookInterface queryBookInterface = new QueryBookInterface();
                queryBookInterface.setVisible(true);
            }
        });
        this.ButtonQuery.setBounds(49, 41, 125, 67);

        contentPane.add(this.ButtonQuery);
        this.ButtonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyBookInterface buyBookInterface = new BuyBookInterface();
                buyBookInterface.setVisible(true);
            }
        });
        this.ButtonBuy.setBounds(226, 41, 125, 67);

        contentPane.add(this.ButtonBuy);
        this.ButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteselfInterface deleteselfInterface = new DeleteselfInterface();
                deleteselfInterface.setVisible(true);
            }
        });
        this.ButtonDel.setBounds(226, 120, 125, 67);

        contentPane.add(this.ButtonDel);
        this.ButtonMyOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyOrderInterface myOrderInterface = new MyOrderInterface();
                myOrderInterface.setVisible(true);
            }
        });
        this.ButtonMyOrder.setBounds(49, 120, 125, 67);

        contentPane.add(this.ButtonMyOrder);
        readername = EnterInterface.readername;
        readerid = EnterInterface.readerid;
        this.LabelNull.setText(readername);
    }
}
