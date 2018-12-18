package chat;

import java.util.Properties;
import java.util.Vector;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

public class ChatClient extends javax.swing.JFrame
{
	private static final long serialVersionUID = 1L;
	private javax.swing.JTextArea jTextAreaNachrichten;
	private javax.swing.JButton jButtonConnect;
	private javax.swing.JTextField jTextFieldName;
	private javax.swing.JList jListUser;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jButtonSenden;
	private javax.swing.JTextField jTextFieldNachricht;
	private Session session;
	private MessageProducer sender;
	private Connection connect;
	private Vector<String> alleUser = new Vector<String>();
	
	public static void main(String args[])
	{
		new ChatClient().setVisible(true);
	}
	
	public ChatClient()
	{
		initComponents();
		getRootPane().setDefaultButton(jButtonSenden);
	}
	
	private void initComponents()
	{
		java.awt.GridBagConstraints gridBagConstraints;
		
		setTitle("Chat Client");
		
		jTextFieldName = new javax.swing.JTextField();
		jButtonConnect = new javax.swing.JButton();
		jTextFieldNachricht = new javax.swing.JTextField();
		jButtonSenden = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaNachrichten = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jListUser = new javax.swing.JList();
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				exitForm(evt);
			}
		});
		
		jLabel2.setText("Name:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
		getContentPane().add(jLabel2, gridBagConstraints);
		
		jTextFieldName.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
		getContentPane().add(jTextFieldName, gridBagConstraints);
		
		jButtonConnect.setText("connect");
		jButtonConnect.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButtonConnectActionPerformed(evt);
			}
		});
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
		getContentPane().add(jButtonConnect, gridBagConstraints);
		
		jTextFieldNachricht.setColumns(20);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
		getContentPane().add(jTextFieldNachricht, gridBagConstraints);
		
		jButtonSenden.setText("senden");
		jButtonSenden.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButtonSendenActionPerformed(evt);
			}
		});
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
		getContentPane().add(jButtonSenden, gridBagConstraints);
		
		jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 200));
		jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 200));
		jTextAreaNachrichten.setEditable(false);
		jTextAreaNachrichten.setColumns(20);
		jScrollPane1.setViewportView(jTextAreaNachrichten);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
		getContentPane().add(jScrollPane1, gridBagConstraints);
		
		jLabel3.setText("Nachricht:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
		getContentPane().add(jLabel3, gridBagConstraints);
		
		jListUser.setPreferredSize(new java.awt.Dimension(100, 10));
		jListUser.setMinimumSize(new java.awt.Dimension(100, 10));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
		getContentPane().add(jListUser, gridBagConstraints);
		
		pack();
	}
	
	private void jButtonSendenActionPerformed(java.awt.event.ActionEvent evt)
	{
		if (session != null)
		{
			try
			{
				String text = jTextFieldName.getText() + ":" + jTextFieldNachricht.getText();
				
				TextMessage msg = session.createTextMessage(text);
				msg.setStringProperty("MessageFormat", "ChatMessage");
				sender.send(msg);
				
				jTextFieldNachricht.setText("");
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt)
	{
		try
		{
			
			Properties env = new Properties();
			env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
			env.setProperty(Context.PROVIDER_URL, "localhost:4447");

		    InitialContext context = new InitialContext(env);
		    ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		    javax.jms.Queue queue = (javax.jms.Queue) context.lookup("queue/test");
		    Connection connect = factory.createConnection();
			
			
			
			
//			Properties p = new Properties();
//			p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//			p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
//			Context ctx = new InitialContext();
//			
//			ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//			
//			javax.jms.Queue queue = (javax.jms.Queue) ctx.lookup("queue/test");
//			
//			connect = factory.createConnection();
			
			session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			sender = session.createProducer(queue);
			
			new Empfaenger();
			
			String anmelden = "NEW:" + jTextFieldName.getText();
			
			TextMessage msg = session.createTextMessage(anmelden);
			msg.setStringProperty("MessageFormat", "ChatMessage");
			sender.send(msg);
			
			jTextFieldName.setEnabled(false);
			jButtonConnect.setEnabled(false);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void exitForm(java.awt.event.WindowEvent evt)
	{
		try
		{
			if (connect != null)
			{
				String abmelden = "DELETE:" + jTextFieldName.getText();
				TextMessage msg = session.createTextMessage(abmelden);
				msg.setStringProperty("MessageFormat", "ChatMessage");
				sender.send(msg);
				connect.close();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.exit(0);
	}
	
	class Empfaenger implements javax.jms.MessageListener
	{
		public Empfaenger()
		{
			try
			{

				Properties env = new Properties();
				env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
				env.setProperty(Context.PROVIDER_URL, "localhost:4447");

			    InitialContext context = new InitialContext(env);
			    ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
			    javax.jms.Topic topic = (javax.jms.Topic) context.lookup("topic/test");
			    Connection connect = factory.createConnection();
				
				
				
				
//				Properties p = new Properties();
//				p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//				p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
//				Context ctx = new InitialContext();
//				ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//				
//				javax.jms.Topic topic = (javax.jms.Topic) ctx.lookup("topic/test");
//				
//				Connection connect = factory.createConnection();
				
				Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
				
				MessageConsumer receiver = session.createConsumer(topic);
				receiver.setMessageListener(this);
				connect.start();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		public void onMessage(Message message)
		{
			try
			{
				TextMessage text = (TextMessage) message;
				String msg = text.getText();
				if (msg.startsWith("NEW:"))
				{
					String user = msg.substring("NEW:".length());
					alleUser.add(user);
					jListUser.setListData(alleUser);
				}
				else if (msg.startsWith("DELETE:"))
				{
					String user = msg.substring("DELETE:".length());
					alleUser.remove(user);
					jListUser.setListData(alleUser);
				}
				else
				{
					jTextAreaNachrichten.append(msg + "\r\n");
					jTextAreaNachrichten.setCaretPosition(jTextAreaNachrichten.getText().length());
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
}
