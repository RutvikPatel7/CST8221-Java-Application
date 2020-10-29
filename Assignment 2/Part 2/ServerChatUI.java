/*
Name - Harpreet Kaur
student number - 040916843

*/import javax.swing.border.TitledBorder;
    /**   /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    // Fig. 27.5: Server.java
    // Server portion of a client/server stream-socket connection. 
    import java.net.Socket;
    import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
    import javax.swing.JFrame;
import javax.swing.JPanel;
    import javax.swing.JScrollPane;
    import javax.swing.JTextArea;
    import javax.swing.JTextField;

    
  /*
     *
     * @author Mr.Nobdy
     */
    public class ServerChatUI extends JFrame  implements Accessible{

      
      
        private Socket socket; // connection to client
        private JTextField message;
        private JButton sendButton;
        private JTextArea display;
        private ObjectOutputStream outputStream;

        private ConnectionWrapper connection;
        
      
    
       /*It must have a constructor that takes a java.net.Socket parameter. In the constructor
you must set the class field socket and call the setFrame() and runClient() methods.
*/
    public ServerChatUI(Socket socket){

    this.socket = socket;
    this.setFrame(this.createUI());
    this.runClient();
    }

     @Override
	public JTextArea getDisplay() {
		return this.display;
	}

   
        /*closeChat()
The method tries to close the connection and then disposes the frame.*/

        @Override
        public void closeChat(){  // auto genterrnnter try and catch 
            try {
                this.connection.closeConnection();
                 this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ServerChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
  


     /*hat method the entire GUI must be created in a
    JPanel and the panel must be returned at the end of the method. It adds a handler of
    type Controller to the Send button*/
   
    	public JPanel createUI() {    
       
    //ActionListener controller = null;
           Controller controller = new Controller();
           JPanel jp = new JPanel(new BorderLayout());
          
    
     
     
  // messgae panel    = mp 
    
 JPanel mp = new JPanel(new BorderLayout());
 JPanel cp = new JPanel(new BorderLayout()); 
    jp.add(mp ,BorderLayout.NORTH); 
    jp.add(cp ,BorderLayout.CENTER);
    
    
    
    javax.swing.border.Border mpb = BorderFactory.createLineBorder(Color.BLUE, 10);
           mp.setBorder(BorderFactory.createTitledBorder(mpb, "MESSAGE"));
 
           
            javax.swing.border.Border cpb = BorderFactory.createLineBorder(Color.BLACK, 10);
        cp.setBorder(BorderFactory.createTitledBorder(cpb, "CHAT DISPLAY" ,TitledBorder.CENTER, TitledBorder.CENTER));
  //Send button msb
     /*The Send button must not be disabled at launch and must have the same height
as the adjacent text field. It must have a mnemonic*/
     
       //test field  mtf
  /*The text field must display the specified text at launch. At launch the text field
must has the focus and (the insertion caret (cursor) must be blinking at the
beginning of the text field. The text field must be editable*/
  JTextField mtf = new JTextField("Type message");
     mtf.setHorizontalAlignment(JTextField.LEFT);
     mtf.setBackground(Color.WHITE);
     mtf.setEditable(true);
    	mtf.requestFocusInWindow();
		mtf.setCaretPosition(0);
     mtf.setPreferredSize(new Dimension(500 ,20));
     
     
     JButton msb= new JButton("Send");
      msb.setEnabled(true);
      msb.addActionListener(controller);
    // what shoulf be hight PreferredSiz or hirght nothing is mentioned anything particlur 
      msb.setMnemonic(KeyEvent.VK_S);
      
     
        
           
/// CHAT DISPLAY panel   = cp 
    mp.add(mtf , BorderLayout.WEST);
     mp.add(msb ,BorderLayout.EAST);
     
     
     

       
   ///e CHAT DISPLAY text area   and scro;; pane 
        JTextArea cta = new JTextArea(30, 45);
	cta.setEditable(false);
        JScrollPane csp = new JScrollPane(cta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED  );
         cp.add(csp ,BorderLayout.CENTER );
    
         
         
 
     
     
     
     
     
     
     
     
     
     return jp;
    }


    /*public final void setFrame() takes a JPanel as a parameter and adds it to the
content pane of the frame. It set the size and the resizable properties of the frame. It
adds a window listener to the frame using an object of WindowController and then
returns*/
    public final void setFrame(JPanel jp){

        this.setContentPane(jp);  
     //WindowController
     this.setSize(new Dimension(588, 500));
     this.setResizable(false);
     this.addWindowListener(new WindowController());
           
    }



    /*private runClient() method takes no parameters and returns nothing. For this part
    of the assignment you can implement the method with an empty body (NOP).*/


    /*The method initializes the connection field with a new ConnectionWrapper. Next, it
uses the connection to create streams and initialize the outputStream field.
Then it creates an object of type Runnable using the 
    ChatRunnable constructor,
creates a thread passing the runnable reference to the Thread constructor and starts
the thread.*/
    private void runClient() {try {
        // empty body (NOP)};
        
        this.connection = new ConnectionWrapper(this.socket);
        this.connection.createStreams();
        this.outputStream = this.connection.getOutputStream();
        
        ChatRunnable <ServerChatUI> run = new ChatRunnable<>(this ,this.connection);
        Thread runthread = new Thread(run);
        runthread.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }

  
    

 
    
    
    
     /* private inner class WindowController which
inherits from WindowAdapter. You have to override the windowClosing() method. For
this part of the assignment you must simply dispose the frame and call System.exit(0)
inside the method.
*/
    private class WindowController extends WindowAdapter{

        @Override
        public void windowClosing( WindowEvent e){
            try {
                System.out.println("ServerUI Window closing!");
                outputStream.writeObject(ChatProtocolConstants.DISPLACMENT
                        +ChatProtocolConstants.CHAT_TERMINATOR
                        +ChatProtocolConstants.LINE_TERMINATOR);
                System.exit(0)
                        ;
            } catch (IOException ex) {
                Logger.getLogger(ServerChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Closing Chat!");
            
            /*Fourth, using the connection it tries to close the connection. If an exception occurs, in
the finally clause of the try-catch statement it disposes the frame*/
    
    try {
         connection.closeConnection();
            } catch (IOException ex) {
                Logger.getLogger(ServerChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally {
        
         dispose();
        }
        System.out.println("Chat closed!");
            
            
            
}
            }
      /*
    private inner class Controller which
inherits from ActionListener. The Controller will handle the Send button events of the
GUI. You have to override the actionPerformed() method. For this part of the
assignment you can implement the method with an empty body (NOP)*/
    private class Controller implements ActionListener{
    
            @Override
          public void  actionPerformed(ActionEvent arg0){
            // empty body (NOP)
            if(arg0.getActionCommand().equals("send")){
                send();
            }
          }

        private void send() {
            
                try {
                    String  sendMessage = message.getText();
                    display.append(sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
                    
                    outputStream.writeObject(ChatProtocolConstants.DISPLACMENT
                            + sendMessage
                            +ChatProtocolConstants.LINE_TERMINATOR);
                    
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (IOException ex) {
                    Logger.getLogger(ServerChatUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    /*The method gets the text from the message text field assigns it to a local variable
sendMessage, appends it to the display adding a line terminator, and then uses the
outputStream to write the following string object:
ChatProtocolConstants.DISPLACMENT
+ sendMessage
+ChatProtocolConstants.LINE_TERMINATOR*/
    
    /*Class ServerChatUI
The ServerChatUI must inherit from JFrame and Accessible.
Fields
The class must have at least the following fields
JTextField message
JButton sendButton
JTextArea display
ObjectOutputStream outputStream
Socket socket
ConnectionWrapper connection
Constructor
ServerChatUI(Socket socket)
See Assignment 2 Part 1.
Methods
JTextArea getDisplay()
closeChat()
The method tries to close the connection and then disposes the frame.
JPanel createUI()
See Assignment 2 Part 1.
runClient()
The method initializes the connection field with a new ConnectionWrapper. Next, it
uses the connection to create streams and initialize the outputStream field.
Then it creates an object of type Runnable using the ChatRunnable constructor,
creates a thread passing the runnable reference to the Thread constructor and starts
the thread.
Inner Classes
Controller
See Assignment 2 Part 1.
Methods
actionPerformed()
In the actionPerformed() if the Send button is clicked the method call the private send()
method of the Controller.
CST8221 – JAP, Assignment 2, Part 2, MMXX Page 5 of 11
send()
The method gets the text from the message text field assigns it to a local variable
sendMessage, appends it to the display adding a line terminator, and then uses the
outputStream to write the following string object:
ChatProtocolConstants.DISPLACMENT
+ sendMessage
+ChatProtocolConstants.LINE_TERMINATOR
If some run-time errors occur during the operation of the method, it must display the
errors on the chat display text area.
WindowController
See Assignment 2 Part 1.
Methods
windowClosing()
First, the method prints on the console the following message:
ServerUI Window closing!
Second, using the outputStream it tries to write the following object:
ChatProtocolConstants.DISPLACMENT
+ChatProtocolConstants.CHAT_TERMINATOR
+ChatProtocolConstants.LINE_TERMINATOR
If an exception occurs during the writing, in the finally clause of the try-catch statement
it disposes the frame.
Third, the method prints on the console the following message:
Closing Chat!
Fourth, using the connection it tries to close the connection. If an exception occurs, in
the finally clause of the try-catch statement it disposes the frame.
Fifth, the method disposes the frame and prints on the console the following message:
Chat closed!
windowClosed()
The method prints on the console the following message:
Server UI Closed!*/
   
    
    
    
    
    
    
    
    
    
    
    
    
    
 }