import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login,signup,clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){                // constructor to open frame as class is run
        setTitle("Automated Teller Machine");
        setLayout(null);
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT); //making image size small
        ImageIcon i3=new ImageIcon(i2);// converting image to Image icon
        JLabel label=new JLabel((i3));
        label.setBounds(70,10,100,100);          //Image location
        add(label);

        JLabel text = new JLabel("Welcome to ATM");            //To Write on the Frame
        text.setFont(new Font("Onward",Font.BOLD,38));   //to change font of text
        text.setBounds(200,40,400,40);
        add(text);

        JLabel cardno = new JLabel("Card No:");            //To Write on the Frame
        cardno.setFont(new Font("Railway",Font.BOLD,28));   //to change font of text
        cardno.setBounds(120,150,150,30);
        add(cardno);

        cardTextField =new JTextField();
        cardTextField.setBounds(300,150,230,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");            //To Write on the Frame
        pin.setFont(new Font("Railway",Font.BOLD,28));   //to change font of text
        pin.setBounds(120,220,250,30);
        add(pin);

        pinTextField =new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD, 14));
        add(pinTextField);

        // Login Button
        login=new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        //clear Button
        clear=new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        //Sign Up Button
        signup=new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);       //To change color of Frame
        setSize(800,400);  //to make frame
        setVisible(true);               // to make frame visible
        setLocation(350,200);     //frame will shift from top left to given location
    }

    public void actionPerformed(ActionEvent ae){   //tells us which button is clicked Very IMP question asked on this for Interface
       if(ae.getSource()== clear){
        cardTextField.setText("");
        pinTextField.setText("");
           
       } else if (ae.getSource()== login) {
           Conn conn=new Conn(); 
           String cardnumber=cardTextField.getText();
           String pinnumber=pinTextField.getText();
           String query = "select * from login where cardnumber='"+cardnumber+"'and pin='"+pinnumber+"'";
           try{
               ResultSet rs=conn.s.executeQuery(query);
               if (rs.next()){
                   setVisible(false);
                   new Transactions(pinnumber).setVisible(true);
               }else {
                   JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
               }

           }catch (Exception e){
               System.out.println(e);
           }
       } else if (ae.getSource()==signup) {

           setVisible(false);
           new SignupOne().setVisible(true);


       }
    }

    public static void main(String[] args) {
    new Login(); //object of main class

        }
    }
