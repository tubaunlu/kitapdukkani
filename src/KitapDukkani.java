import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KitapDukkani {

	private JFrame frame;
	private JTextField textbname;
	private JTextField textprice;
	private JTable table;
	private JTextField textbid;
	
	  private Connection con;
	  private PreparedStatement pst;
      private ResultSet rs;
      private JTextField textauthorName;
      private JTextField textauthorSurname;
      private JTextField textedition;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapDukkani window = new KitapDukkani();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public KitapDukkani() {
		initialize();
	    Connect();
	    table_load();
	}
	//veri tabanının kontrol kısmı
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproje", "root","");
        }
           catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
          
            JOptionPane.showMessageDialog(null, "Veritabanı bağlantısı kurulurken bir hata oluştu!");
            
         
        }
     
    }
	public void closeConnection() {
	    try {
	        if (con != null && !con.isClosed()) {
	            con.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Veritabanı bağlantısı kapatılırken bir hata oluştu!");
	    }
	}

	
	
	
	public void table_load()
	{
	    try 
	    {
	    pst = con.prepareStatement("select * from book");
	    ResultSet rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
	    catch (SQLException e) 
	     {
	        e.printStackTrace();
	  } 
	}
	  	

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 192));
		frame.setBounds(100, 100, 790, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kitap Dükkanı");
		lblNewLabel.setForeground(new Color(64, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(295, 0, 226, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(new TitledBorder(null, "Kay\u0131t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(2, 66, 282, 213);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kitabın Adı :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(10, 22, 107, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Baskı Sayısı :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1_1.setBounds(10, 137, 107, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Kitabın Fiyatı :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1_2.setBounds(10, 173, 107, 26);
		panel.add(lblNewLabel_1_2);
		
		textbname = new JTextField();
		textbname.setBounds(117, 23, 156, 27);
		panel.add(textbname);
		textbname.setColumns(10);
		
		textprice = new JTextField();
		textprice.setColumns(10);
		textprice.setBounds(119, 176, 154, 26);
		panel.add(textprice);
		
		JLabel lblNewLabel_1_4 = new JLabel("Yazarın Adı :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1_4.setBounds(10, 59, 107, 29);
		panel.add(lblNewLabel_1_4);
		
		textauthorName = new JTextField();
		textauthorName.setColumns(10);
		textauthorName.setBounds(117, 60, 156, 26);
		panel.add(textauthorName);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Yazarın Soyadı :");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1_4_1.setBounds(10, 99, 117, 27);
		panel.add(lblNewLabel_1_4_1);
		
		textauthorSurname = new JTextField();
		textauthorSurname.setColumns(10);
		textauthorSurname.setBounds(117, 98, 156, 26);
		panel.add(textauthorSurname);
		
		textedition = new JTextField();
		textedition.setColumns(10);
		textedition.setBounds(119, 139, 154, 26);
		panel.add(textedition);
		
	     // Kullanıcı arayüzü oluşturdugumuz kısım
        JTextField txtbname = new JTextField();
        JTextField txtedition = new JTextField();
        JTextField txtprice = new JTextField();
        JTextField txtauthorName = new JTextField();
        JTextField txtauthorSurname = new JTextField();
        JButton btnNewButton = new JButton("Kayıt Et");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Use the class-level JTextField objects
                    String bname = textbname.getText().trim();
                    String authorName = textauthorName.getText().trim();
                    String authorSurname = textauthorSurname.getText().trim();
                    String edition = textedition.getText().trim();
                    String price = textprice.getText().trim();
                    
                    if (bname.isEmpty() || (authorName.isEmpty()) || (authorSurname.isEmpty()) || edition.isEmpty() || price.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
                        return;
                    }
                    
                    pst = con.prepareStatement("INSERT INTO book (name,authorname,authorsurname, edition, price) VALUES (?, ?, ?,?,?)");
                    pst.setString(1, bname);
                    pst.setString(2, authorName);
                    pst.setString(3, authorSurname);
                    pst.setString(4, edition);
                    pst.setString(5, price);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Kayıt Eklendi!");
                    table_load();
                    textbname.setText("");
                    textauthorName.setText("");
                    textauthorSurname.setText("");
                    textedition.setText("");
                    textprice.setText("");
                    textbname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Kayıt eklenirken bir hata oluştu!");
                }
            }
        });

		btnNewButton.setForeground(new Color(64, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.setBounds(10, 313, 81, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnk = new JButton("Çıkış");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int choice = JOptionPane.showConfirmDialog(null, "Çıkmak istediğinize emin misiniz?", "Çıkış", JOptionPane.YES_NO_OPTION);
			        if (choice == JOptionPane.YES_OPTION) {
			            closeConnection(); // Veritabanı bağlantısını kapat
			            System.exit(0); // Uygulamayı kapat
			
			
			
			
			        }}

		});
		btnk.setForeground(new Color(64, 0, 0));
		btnk.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnk.setBounds(101, 313, 81, 44);
		frame.getContentPane().add(btnk);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 temizle1(); // Temizleme işlemi
		    }
	
			
			
		private void temizle1() {
		    textbname.setText("");
		    textauthorName.setText("");
		    textauthorSurname.setText("");
		    textedition.setText("");
		    textprice.setText("");
		    textbid.setText("");
		    textbname.requestFocus();
		
		}
			    
		});
		btnTemizle.setForeground(new Color(64, 0, 0));
		btnTemizle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnTemizle.setBounds(192, 313, 81, 44);
		frame.getContentPane().add(btnTemizle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 64, 487, 318);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBorder(new TitledBorder(null, "Arama", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 384, 258, 53);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kitap ID:");
		lblNewLabel_1_3.setBounds(10, 13, 102, 24);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panel_1.add(lblNewLabel_1_3);
		
		textbid = new JTextField();
		JTextField txtbname1 = new JTextField();
		JTextField txtauthorName1 = new JTextField();
		JTextField txtauthorSurname1 = new JTextField();
		JTextField txtedition1 = new JTextField();
		JTextField txtprice1 = new JTextField();
	    
		textbid.addKeyListener(new KeyAdapter() {
		
		
	        @Override
			public void keyReleased(KeyEvent e) {
				 try {
	                    String id = textbid.getText().trim();
	                    pst = con.prepareStatement("select name,authorname,authorsurname, edition, price from book where id = ?");
	                    pst.setString(1, id);
	                    ResultSet rs = pst.executeQuery();
	                    
						if (rs.next()) {
	                        String name = rs.getString(1);
	                        String authorname = rs.getString(2);
	                        String authorsurname = rs.getString(3);
	                        String edition = rs.getString(4);
	                        String price = rs.getString(5);

	                        txtbname1.setText(name);
	                        txtauthorName1.setText(authorname);
	                        txtauthorSurname1.setText(authorsurname);
	                        txtedition1.setText(edition);
	                        txtprice1.setText(price);

	                    } else {
	                        txtbname1.setText("");
	                        txtauthorName1.setText("");
	                        txtauthorSurname1.setText("");
	                        txtedition1.setText("");
	                        txtprice1.setText("");
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	       
			
		});
		textbid.setColumns(10);
		textbid.setBounds(79, 15, 166, 24);
		panel_1.add(textbid);
		
		
	    	JTextField bid = new JTextField(); 
	       JButton btnGncelle = new JButton("Güncelle");
		   btnGncelle.addActionListener(new ActionListener() {
			
			
			   @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    // Use the class-level JTextField objects
	                	

                         String bname = textbname.getText().trim();
	                     String authorName = textauthorName.getText().trim();
	                     String authorSurname = textauthorSurname.getText().trim();
	                     String edition = textedition.getText().trim();
	                     String price = textprice.getText().trim();
	                     String bid = textbid.getText().trim();

	                 	if (bid.isEmpty()) {
	    				    JOptionPane.showMessageDialog(null, "Lütfen bir Kitap ID'si girin!");
	    				    return;
	    				}

	                 	  if (bname.isEmpty() || (authorName.isEmpty()) || (authorSurname.isEmpty()) || edition.isEmpty() || price.isEmpty()) {
	                          JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
	                          return;
	                      }
	    				

	                     // Güncelleme işlemi için PreparedStatement oluşturuluyor (
	                     pst = con.prepareStatement("UPDATE book SET name=?, authorname=?, authorsurname=?, edition=?, price=? WHERE id = ?");
	                     pst.setString(1, bname);
	                     pst.setString(2, authorName);
	                     pst.setString(3, authorSurname);
	                     pst.setString(4, edition);
	                     pst.setString(5, price);
	                     pst.setString(6, bid);

	                     // Güncelleme işlemi gerçekleştiriliyor
	                     int rowsAffected = pst.executeUpdate();

	                     if (rowsAffected > 0) {
	                         JOptionPane.showMessageDialog(null, "Kayıt Güncellendi!");
	                         table_load();
	                         textbname.setText("");
	                         textauthorName.setText("");
	                         textauthorSurname.setText("");
	                         textedition.setText("");
	                         textprice.setText("");
	                         textbid.setText("");
	                         textbname.requestFocus();
	                     } else {
	                         JOptionPane.showMessageDialog(null, "Belirtilen Kitap ID'si ile kayıt bulunamadı veya güncelleme sırasında bir hata oluştu!");
	                     }
	                 } catch (SQLException e1) {
	                     e1.printStackTrace();
	                     JOptionPane.showMessageDialog(null, "Kayıt güncellenirken bir hata oluştu!");
	                 }
	             }
		});
		btnGncelle.setForeground(new Color(64, 0, 0));
		btnGncelle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnGncelle.setBounds(434, 393, 87, 44);
		frame.getContentPane().add(btnGncelle);
	  
	JTextField bid1 = new JTextField(); 
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				    
				String bid = textbid.getText().trim();

				if (bid.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Lütfen bir Kitap ID'si girin!");
				    return;
				}

		            
		            try {
		                   pst = con.prepareStatement("delete from book where id =?");
		           
		                   pst.setString(1, bid);
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Kayıt Silindi!!!!!");
		                   table_load();
		                   txtbname1.setText("");
		                   txtauthorName1.setText("");
		                   txtauthorSurname1.setText("");    
		                   txtedition1.setText("");
		                   txtprice1.setText("");
		                   txtbname1.requestFocus();
		               }
		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		                   JOptionPane.showMessageDialog(null, "Kayıt silinirken bir hata oluştu!");
		               }
		               }
		
		});
		btnSil.setForeground(new Color(64, 0, 0));
		btnSil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnSil.setBounds(561, 393, 87, 44);
		frame.getContentPane().add(btnSil);
	}
}
