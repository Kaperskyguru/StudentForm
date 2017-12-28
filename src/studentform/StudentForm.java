package studentform;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudentForm extends JFrame {

    JTable table;
    JPanel sidelayout;
    JPanel toplayout;
    JPanel centerlayout = new JPanel();
    JPanel form = new JPanel();
    JPanel form1 = new JPanel();
    JPanel nextForm = new JPanel();
    JLabel pic;
    JTextField ln, fn, emailfield, phoneNo, adresField, nxtOfKin;
    JComboBox nat, state, month, date, year;
    Button browse;
    public JFileChooser jFileChooser = new JFileChooser();
    JRadioButton male, female;
    ButtonGroup group;
    Font font = new Font("Calibr", Font.BOLD, 15);

    Connection con;
    Statement st;
    ResultSet rs;

    int xMouse;
    int yMouse;

    public StudentForm(BorderLayout borderLayout) {

        //JFrame f = new JFrame();
        //f.setU
        drag();

        toplayout = new JPanel();
        toplayout.setBackground(Color.RED);
        add(toplayout, BorderLayout.NORTH);

        JLabel label = new JLabel();
        label.setForeground(Color.YELLOW);
        label.setText("Student Registration Form");
        label.setFont(font);
        toplayout.add(label);

        sidelayout = new JPanel(new BorderLayout());
        sidelayout.setBackground(Color.RED);
        add(sidelayout, BorderLayout.WEST);

        JPanel Reglayout = new JPanel(new GridBagLayout());
        Reglayout.setBackground(Color.red);
        sidelayout.add(Reglayout, BorderLayout.NORTH);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.VERTICAL;
        gbc1.insets = new Insets(31, 10, 10, 10);
        gbc1.weightx = 0.01;

        JButton Register = new JButton("Registration");
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        Reglayout.add(Register, gbc1);

        JButton viewRecord = new JButton("View Record");
        viewRecord.setVerticalTextPosition(AbstractButton.CENTER);
        viewRecord.setHorizontalTextPosition(AbstractButton.LEADING);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        Reglayout.add(viewRecord, gbc1);

        JButton Result = new JButton("Result");
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        Reglayout.add(Result, gbc1);
        add(sidelayout, BorderLayout.WEST);

        viewRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                form.setVisible(true);
                //form.setSize(700, 700);
                form.setBackground(Color.BLUE);
                add(form, BorderLayout.CENTER);
                try {
                    while (rs.next()) {
                        String f1 = rs.getString(1).toString();
                        String f2 = rs.getString(2).toString();
                        String f3 = rs.getString(3).toString();
                        String f4 = rs.getString(4).toString();
                        String f5 = rs.getString(5).toString();
                        String f6 = rs.getString(6).toString();
                        String f7 = rs.getString(7).toString();
                        String f8 = rs.getString(8).toString();
                        String f9 = rs.getString(9).toString();
                        String f10 = rs.getString(10).toString();
                        String[] colNames = {"S//N", "First Name", "Last Name", "Email", "Phone No", "Gender", "Address", "Next Of Kin", "Nationality", "State Of Origin", "Date Of Birth", "Qualification", "Exam No", "Subject", "Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"};
                        Object[][] string = {{f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f5, f6, f7, f8, f9, f10}};

                        table = new JTable(string, colNames);
                        //25
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                form.add(new JScrollPane(table));

                centerlayout.setVisible(false);
                form1.setVisible(false);
                nextForm.setVisible(false);

            }

        });

        Register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                form();
                form.setVisible(false);
                form1.setVisible(false);
                nextForm.setVisible(false);

            }

        });

        Result.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // form1 = new JPanel();
                form1.setVisible(true);
                form1.setSize(700, 700);
                form1.setBackground(Color.GREEN);
                add(form1, BorderLayout.CENTER);

                form.setVisible(false);
                centerlayout.setVisible(false);
                nextForm.setVisible(false);

            }

        });

        form();
        connect();

    }

    public void drag() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }

            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }

        });

    }

    public void connect() {

        try {

            try {
                //
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            con = DriverManager.getConnection("jdbc:odbc:form");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String InsertToDB = "select * from Tableform";
            rs = st.executeQuery(InsertToDB);
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
//                        + rs.getString(4) + rs.getString(5) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7)
//                        + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
//            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public JTable table(){
//        
//        JTable table1 = null;
//         try{
//            
//            String[] colNames = {"First Name", "Last Name", "Email", "Phone No"};
//            Object[][] string = {{"sfsd","asd","asda","asdasdas"},
//                            {"sfsd","asd","asda","asdasdas"}};
//        table1  = new JTable(string, colNames);
//         }
//         catch(Exception e){
//             
//         }
    // /**/},{/*f1, f2, f3,f4,f5,f6,f7,f8,f9,f10*/}
    //"f1", "f2", "f3","f4","f5","f6","f7","f8","f9","f10"
    //  return table1;
    //  }
    public void form() {

        centerlayout.setSize(700, 700);
        centerlayout.setLayout(new BorderLayout());
        centerlayout.setBackground(Color.GREEN);
        centerlayout.setVisible(true);
        add(centerlayout, BorderLayout.CENTER);

        JPanel pane = new JPanel(new GridBagLayout());
        pane.setBackground(Color.BLUE);
        pane.setVisible(true);
        pane.setSize(700, 700);
        centerlayout.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setSize(300, 100);
        JLabel SDTREG = new JLabel("Student Information");
        panel.add(SDTREG, BorderLayout.NORTH);
        centerlayout.add(panel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(31, 10, 10, 10);
        gbc.weightx = 0.01;

        JLabel FN = new JLabel("First Name:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pane.add(FN, gbc);

        JLabel LN = new JLabel("Last Name:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pane.add(LN, gbc);

        JLabel email = new JLabel("Email:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        pane.add(email, gbc);

        JLabel phone = new JLabel("Phone No.:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        pane.add(phone, gbc);

        JLabel gender = new JLabel("Gender:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        pane.add(gender, gbc);

        JLabel address = new JLabel("Address:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;

        pane.add(address, gbc);

        JLabel nextOfKin = new JLabel("Next of Kin");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        pane.add(nextOfKin, gbc);

        JLabel nationality = new JLabel("Nationality:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;

        pane.add(nationality, gbc);

        JLabel stateOfOrigin = new JLabel("State of Origin:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        pane.add(stateOfOrigin, gbc);

        JLabel DOB = new JLabel("Date Of Birth:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        pane.add(DOB, gbc);

        fn = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pane.add(fn, gbc);

        ln = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pane.add(ln, gbc);

        emailfield = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        pane.add(emailfield, gbc);

        phoneNo = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pane.add(phoneNo, gbc);

        JPanel b = new JPanel();
        b.setBackground(Color.BLUE);
        b.setLayout(new BorderLayout());
        group = new ButtonGroup();
        male = new JRadioButton("Male", true);
        female = new JRadioButton("Female", false);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 4;

        b.add(male, BorderLayout.WEST);
        b.add(female, BorderLayout.EAST);
        pane.add(b, gbc);
        group.add(male);
        group.add(female);

        adresField = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 5;
        pane.add(adresField, gbc);

        nxtOfKin = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 6;
        pane.add(nxtOfKin, gbc);

        nat = new JComboBox();
        nat.addItem("Nigeria");
        nat.addItem("Ghana");
        nat.addItem("South Africa");
        nat.addItem("United State");
        nat.addItem("America");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 3;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 7;
        pane.add(nat, gbc);

        state = new JComboBox();
        state.addItem("Rivers State");
        state.addItem("Abia State");
        state.addItem("Adamawa State");
        state.addItem("Akwa Ibom State");
        state.addItem("Cross Rivers State");
        state.addItem("Rivers State");
        state.addItem("Abia State");
        state.addItem("Adamawa State");
        state.addItem("Akwa Ibom State");
        state.addItem("Cross Rivers State");
        gbc.ipadx = 1;
        gbc.gridx = 1;
        gbc.gridy = 8;
        pane.add(state, gbc);

        JPanel dobPane = new JPanel();
        dobPane.setBackground(Color.BLUE);
        JLabel monthText = new JLabel("Month:");
        JLabel dateText = new JLabel("Date:");
        JLabel yearText = new JLabel("Year:");

        month = new JComboBox();
        month.addItem("Jan.");
        month.addItem("Feb.");
        month.addItem("Mar.");
        month.addItem("April");
        month.addItem("May");
        month.addItem("June");
        month.addItem("July");
        month.addItem("Aug.");
        month.addItem("Sept.");
        month.addItem("Oct.");
        month.addItem("Dec.");
        date = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            date.addItem("" + i);
        }
        year = new JComboBox();

        for (int i = 1970; i <= 2015; i++) {
            year.addItem("" + i);
        }

        gbc.gridx = 1;
        gbc.gridy = 9;
        dobPane.add(monthText);
        dobPane.add(month);
        dobPane.add(dateText);
        dobPane.add(date);
        dobPane.add(yearText);
        dobPane.add(year);
        pane.add(dobPane, gbc);

        browse = new Button("Browse");
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                open();
            }

        });

        gbc.gridx = 2;
        gbc.ipady = 1;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        pane.add(browse, gbc);

        JPanel p = new JPanel();
        pic = new JLabel();
        p.setBackground(Color.CYAN);
        p.setSize(50, 50);
        p.add(pic);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipady = 1;
        gbc.ipadx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        pane.add(p, gbc);

        JPanel nextPane = new JPanel(new BorderLayout());
        nextPane.setBackground(Color.BLUE);
        Button Next = new Button("Next");
        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
                centerlayout.setVisible(false);
                form.setVisible(false);
                form1.setVisible(false);

            }

        });

        nextPane.add(Next, BorderLayout.EAST);
        Button SaveBT = new Button("Save");
        SaveBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatabaseDB();

            }

        });

        nextPane.add(SaveBT, BorderLayout.WEST);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipady = 1;
        gbc.ipadx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        pane.add(nextPane, gbc);

    }

    public void next() {

        nextForm.setSize(500, 700);
        nextForm.setLayout(new BorderLayout());
        nextForm.setBackground(Color.GREEN);
        nextForm.setVisible(true);
        add(nextForm, BorderLayout.CENTER);

        JPanel pane = new JPanel(new GridBagLayout());
        pane.setBackground(Color.BLUE);
        pane.setVisible(true);
        pane.setSize(700, 700);
        nextForm.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setSize(300, 100);
        JLabel SDTREG = new JLabel("Examination Details");
        panel.add(SDTREG, BorderLayout.NORTH);
        nextForm.add(panel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(31, 10, 10, 10);
        gbc.weightx = 0.01;

        // gbc.gridx = 0;
        JLabel Qualification = new JLabel("Qualification:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pane.add(Qualification, gbc);

        JLabel ExamNo = new JLabel("Exam No:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pane.add(ExamNo, gbc);

        JLabel session = new JLabel("Session");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        pane.add(session, gbc);

        JLabel subject1 = new JLabel("Subject:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        pane.add(subject1, gbc);

        JLabel subject2 = new JLabel("Subject:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        pane.add(subject2, gbc);

        JLabel subject3 = new JLabel("Subject:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        pane.add(subject3, gbc);

        JLabel subject4 = new JLabel("Subject:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        pane.add(subject4, gbc);

        JLabel subject5 = new JLabel("Subject:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;

        pane.add(subject5, gbc);

        JComboBox QualificationBox = new JComboBox();
        QualificationBox.addItem("WAEC");
        QualificationBox.addItem("NECO");
        QualificationBox.addItem("GCE");
        QualificationBox.addItem("NAPTEB");
        QualificationBox.addItem("OTHERS");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 3;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 0;
        pane.add(QualificationBox, gbc);

        JTextField ExamNoBox = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 1;
        pane.add(ExamNoBox, gbc);

        JComboBox Subject1Box = new JComboBox();
        Subject1Box.addItem("Mathematics");
        Subject1Box.addItem("English Language");
        Subject1Box.addItem("Biology");
        Subject1Box.addItem("Chemistry");
        Subject1Box.addItem("Physics");
        Subject1Box.addItem("Chemistry");
        gbc.ipadx = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pane.add(Subject1Box, gbc);

        JComboBox Subject2Box = new JComboBox();
        Subject2Box.addItem("Mathematics");
        Subject2Box.addItem("English Language");
        Subject2Box.addItem("Biology");
        Subject2Box.addItem("Chemistry");
        Subject2Box.addItem("Physics");
        Subject2Box.addItem("Chemistry");
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 4;
        pane.add(Subject2Box, gbc);

        JComboBox Subject3Box = new JComboBox();
        Subject3Box.addItem("Mathematics");
        Subject3Box.addItem("English Language");
        Subject3Box.addItem("Biology");
        Subject3Box.addItem("Chemistry");
        Subject3Box.addItem("Physics");
        Subject3Box.addItem("Chemistry");
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 5;
        pane.add(Subject3Box, gbc);

        JComboBox Subject4Box = new JComboBox();
        Subject4Box.addItem("Mathematics");
        Subject4Box.addItem("English Language");
        Subject4Box.addItem("Biology");
        Subject4Box.addItem("Chemistry");
        Subject4Box.addItem("Physics");
        Subject4Box.addItem("Chemistry");
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 6;
        pane.add(Subject4Box, gbc);

        JComboBox Subject5Box = new JComboBox();
        Subject5Box.addItem("Mathematics");
        Subject5Box.addItem("English Language");
        Subject5Box.addItem("Biology");
        Subject5Box.addItem("Chemistry");
        Subject5Box.addItem("Physics");
        Subject5Box.addItem("Chemistry");
        gbc.gridx = 1;
        gbc.ipadx = 1;
        gbc.gridy = 7;
        pane.add(Subject5Box, gbc);

        JComboBox OtherSub1 = new JComboBox();;
        for (char i = 'A'; i <= 'F'; i++) {
            OtherSub1.addItem("" + i);
        }
        gbc.gridx = 2;
        gbc.ipadx = 1;
        gbc.gridy = 3;
        pane.add(OtherSub1, gbc);

        JComboBox OtherSub2 = new JComboBox();
        for (char i = 'A'; i <= 'F'; i++) {
            OtherSub2.addItem("" + i);
        }
        gbc.gridx = 2;
        gbc.ipadx = 1;
        gbc.gridy = 4;
        pane.add(OtherSub2, gbc);

        JComboBox OtherSub3 = new JComboBox();
        for (char i = 'A'; i <= 'F'; i++) {
            OtherSub3.addItem("" + i);
        }
        gbc.gridx = 2;
        gbc.ipadx = 1;
        gbc.gridy = 5;
        pane.add(OtherSub3, gbc);

        JComboBox OtherSub4 = new JComboBox();
        for (char i = 'A'; i <= 'F'; i++) {
            OtherSub4.addItem("" + i);
        }
        gbc.gridx = 2;
        gbc.ipadx = 1;
        gbc.gridy = 6;
        pane.add(OtherSub4, gbc);

        JComboBox OtherSub5 = new JComboBox();
        for (char i = 'A'; i <= 'F'; i++) {
            OtherSub5.addItem("" + i);
        }
        gbc.gridx = 2;
        gbc.ipadx = 1;
        gbc.gridy = 7;
        pane.add(OtherSub5, gbc);

        Button PreviousButton = new Button("Previous");
        PreviousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                form();
                nextForm.setVisible(false);

            }

        });
        gbc.gridx = 2;
        gbc.ipady = 1;
        gbc.gridy = 8;
        pane.add(PreviousButton, gbc);

        Button SaveAllButton = new Button("Save All");
        gbc.gridx = 3;
        gbc.ipady = 1;
        gbc.gridy = 8;
        pane.add(SaveAllButton, gbc);

    }

    public void DatabaseDB() {
        String firstname = fn.getText();
        String lastname = ln.getText();
        String email = emailfield.getText();
        try {
            rs.moveToInsertRow();
            rs.updateString("firstname", firstname);
            rs.updateString("lastname", lastname);
            rs.updateString("email", email);
            rs.insertRow();
            System.out.println("Record update");

            st.close();
            rs.close();
            String ToDB = "select * from Tableform";
            st = con.prepareStatement(ToDB);
            //String InsertToDB = "select * from Tableform";
            rs = st.executeQuery(ToDB);
            //table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Record not update");
        }

    }

    public void open() {
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif");
        jFileChooser.addChoosableFileFilter(filter);
        int result = jFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectecdfile = jFileChooser.getSelectedFile();
            String path = selectecdfile.getAbsolutePath();
            ResizedImage(path);
            pic.setIcon(ResizedImage(path));

        }

    }

    public ImageIcon ResizedImage(String ImagePath) {
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image image = myImage.getImage();
        Image newImage = image.getScaledInstance(150, 142, Image.SCALE_SMOOTH);
        ImageIcon mynewImage = new ImageIcon(newImage);
        return mynewImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                StudentForm studentForm = new StudentForm(new BorderLayout());
                studentForm.setTitle("Student Form");
                studentForm.setSize(700, 700);
                studentForm.setLocationRelativeTo(null);
                studentForm.setVisible(true);
                studentForm.setResizable(false);
                studentForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

        });

    }

}
