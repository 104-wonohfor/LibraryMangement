/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import DAO.BookRequestDAO;
import DAO.ReaderCardDAO;
import DAO.RequestDAO;
import model.BookRequest;
import model.Reader;
import model.ReaderCard;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Thang
 */
public class UserRequest extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    private String cardID;
    
    public UserRequest(Home home, String cardID) {
        setTitle("Your Request");
        this.home = home;
        this.cardID = cardID;
        initComponents();
        loadDefault();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                home.setEnabled(true);
            }
        });
    }
    
    private void loadDefault(){
        loadTableBookRequest();
        loadTableReaderCard();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblBookRequest.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }

    private void loadTableReaderCard() {
        ReaderCard readerCard = new ReaderCardDAO().getReaderCardByCardID(cardID);

        String[][] data = new String[1][4];

        data[0][0] = readerCard.getCardID();
        data[0][1] = readerCard.getCardStatus();
        data[0][2] = readerCard.getDateIssued();
        data[0][3] = readerCard.getDateExpired();    
        
        tblReaderCard.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "Card ID", "Card Status", "Date Issued", "Date Expired"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột 
        tblReaderCard.getColumnModel().getColumn(0).setPreferredWidth(150); // Card ID
        tblReaderCard.getColumnModel().getColumn(1).setPreferredWidth(150); // Card Status
        tblReaderCard.getColumnModel().getColumn(2).setPreferredWidth(150); // Date Issued
        tblReaderCard.getColumnModel().getColumn(3).setPreferredWidth(150); // Date Expired

        customizeComponents();
    }

    private void loadTableBookRequest() {
        List<BookRequest> bookRequests = new BookRequestDAO().getAllBookRequestByCardID(cardID);

        String[][] data = new String[bookRequests.size()][9];

        for (int i = 0; i < bookRequests.size(); i++) {
            data[i][0] = String.valueOf(bookRequests.get(i).getBookRequestID());
            data[i][1] = bookRequests.get(i).getBookCode().toString().replace("[", "").replace("]", "");
            data[i][2] = bookRequests.get(i).getCardID();
            data[i][3] = bookRequests.get(i).getStatus();
            data[i][4] = bookRequests.get(i).getBorrowDate();
            data[i][5] = bookRequests.get(i).getDueDate();
            data[i][6] = bookRequests.get(i).getReturnDate();
            data[i][7] = String.valueOf(bookRequests.get(i).getPenaltyFee());
            data[i][8] = bookRequests.get(i).getNote();
        }

        tblBookRequest.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "Request ID", "Book Code", "Card ID", "Status", "Borrow Date", "Due Date", "Return Date", "Penalty Fee", "Note"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột
        tblBookRequest.getColumnModel().getColumn(0).setPreferredWidth(80); // Request ID
        tblBookRequest.getColumnModel().getColumn(1).setPreferredWidth(200); // Book Code
        tblBookRequest.getColumnModel().getColumn(2).setPreferredWidth(100); // Card ID
        tblBookRequest.getColumnModel().getColumn(3).setPreferredWidth(100); // Status
        tblBookRequest.getColumnModel().getColumn(4).setPreferredWidth(100); // Borrow Date
        tblBookRequest.getColumnModel().getColumn(5).setPreferredWidth(100); // Due Date
        tblBookRequest.getColumnModel().getColumn(6).setPreferredWidth(100); // Return Date
        tblBookRequest.getColumnModel().getColumn(7).setPreferredWidth(100); // Penalty Fee
        tblBookRequest.getColumnModel().getColumn(8).setPreferredWidth(150); // Note

        // set scroll bar to the beginning
        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);

        customizeComponents();
    }

    private void customizeComponents() {
        // Thay đổi màu của các thanh cuộn


        // Thay đổi nền của tiêu đề bảng
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(240,222,54));
        for (int i = 0; i < tblBookRequest.getColumnModel().getColumnCount(); i++) {
            tblBookRequest.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackgroundPanel = new javax.swing.JPanel();
        BookRequestPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TableScrollPanel = new javax.swing.JScrollPane();
        tblBookRequest = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnCancelRequest = new javax.swing.JButton();
        ReaderCardPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReaderCard = new javax.swing.JTable();
        cbDuration = new javax.swing.JComboBox<>();
        btnRequestExtend = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtRetypeNewPIN = new javax.swing.JPasswordField();
        txtNewPIN = new javax.swing.JPasswordField();
        txtOldPIN = new javax.swing.JPasswordField();
        EyeButton = new javax.swing.JButton();
        EyeButton1 = new javax.swing.JButton();
        EyeButton2 = new javax.swing.JButton();
        btnEditPIN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/1200px-Logo_Hust.png")));

        BackgroundPanel.setBackground(new java.awt.Color(13, 18, 130));

        BookRequestPanel.setBackground(new java.awt.Color(13, 18, 130));
        BookRequestPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Book Requests");

        tblBookRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableScrollPanel.setViewportView(tblBookRequest);

        btnCancelRequest.setBackground(new java.awt.Color(255, 51, 0));
        btnCancelRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancelRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelRequest.setText("Cancel Request");
        btnCancelRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BookRequestPanelLayout = new javax.swing.GroupLayout(BookRequestPanel);
        BookRequestPanel.setLayout(BookRequestPanelLayout);
        BookRequestPanelLayout.setHorizontalGroup(
            BookRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookRequestPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(BookRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(BookRequestPanelLayout.createSequentialGroup()
                        .addGroup(BookRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TableScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookRequestPanelLayout.createSequentialGroup()
                                .addComponent(btnCancelRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(359, 359, 359)))
                        .addComponent(jLabel2)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        BookRequestPanelLayout.setVerticalGroup(
            BookRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookRequestPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookRequestPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        ReaderCardPanel.setBackground(new java.awt.Color(13, 18, 130));

        tblReaderCard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReaderCard);

        cbDuration.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "1 Month", "3 Months", "6 Months", "1 Year" }));

        btnRequestExtend.setBackground(new java.awt.Color(0, 153, 0));
        btnRequestExtend.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRequestExtend.setForeground(new java.awt.Color(255, 255, 255));
        btnRequestExtend.setText("Request Extend");
        btnRequestExtend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestExtendActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(215, 19, 19));
        jLabel30.setText("Old PIN");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(215, 19, 19));
        jLabel31.setText("New PIN");

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(215, 19, 19));
        jLabel32.setText("Retype New PIN");
        jLabel32.setToolTipText("");

        txtRetypeNewPIN.setToolTipText("");

        EyeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        EyeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EyeButtonActionPerformed(evt);
            }
        });

        EyeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        EyeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EyeButton1ActionPerformed(evt);
            }
        });

        EyeButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        EyeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EyeButton2ActionPerformed(evt);
            }
        });

        btnEditPIN.setBackground(new java.awt.Color(240, 222, 54));
        btnEditPIN.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEditPIN.setForeground(new java.awt.Color(215, 19, 19));
        btnEditPIN.setText("Edit PIN");
        btnEditPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPINActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Reader Card");

        javax.swing.GroupLayout ReaderCardPanelLayout = new javax.swing.GroupLayout(ReaderCardPanel);
        ReaderCardPanel.setLayout(ReaderCardPanelLayout);
        ReaderCardPanelLayout.setHorizontalGroup(
            ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReaderCardPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReaderCardPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReaderCardPanelLayout.createSequentialGroup()
                        .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNewPIN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOldPIN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRetypeNewPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EyeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EyeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                                .addComponent(EyeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(70, 70, 70)
                        .addComponent(btnRequestExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
            .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ReaderCardPanelLayout.setVerticalGroup(
            ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReaderCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRequestExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                            .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(EyeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtOldPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(ReaderCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                                    .addComponent(txtNewPIN)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtRetypeNewPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(ReaderCardPanelLayout.createSequentialGroup()
                                    .addComponent(EyeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(EyeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReaderCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BookRequestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addComponent(ReaderCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditPINActionPerformed(java.awt.event.ActionEvent evt) {
        String oldPIN = new String(txtOldPIN.getPassword());
        String newPIN = new String(txtNewPIN.getPassword());
        String retypeNewPIN = new String(txtRetypeNewPIN.getPassword());

        if (oldPIN.isEmpty() || newPIN.isEmpty() || retypeNewPIN.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!new ReaderCardDAO().checkPIN(cardID, oldPIN)) {
            JOptionPane.showMessageDialog(null, "Old PIN is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!newPIN.equals(retypeNewPIN)) {
            JOptionPane.showMessageDialog(null, "New PIN and Retype New PIN do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!newPIN.matches("\\d{6}") || !retypeNewPIN.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "New PIN must be 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (newPIN.equals(oldPIN)) {
            JOptionPane.showMessageDialog(null, "New PIN must be different from Old PIN", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (new ReaderCardDAO().updatePIN(cardID, newPIN)) {
            JOptionPane.showMessageDialog(null, "PIN has been updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            txtOldPIN.setText("");
            txtNewPIN.setText("");
            txtRetypeNewPIN.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update PIN", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void EyeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (EyeButton.getIcon().toString().contains("hide")) {
            EyeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png")));
            txtOldPIN.setEchoChar((char) 0);
        } else {
            EyeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png")));
            txtOldPIN.setEchoChar('*');
        }
    }

    private void EyeButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (EyeButton1.getIcon().toString().contains("hide")) {
            EyeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png")));
            txtNewPIN.setEchoChar((char) 0);
        } else {
            EyeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png")));
            txtNewPIN.setEchoChar('*');
        }
    }

    private void EyeButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (EyeButton2.getIcon().toString().contains("hide")) {
            EyeButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png")));
            txtRetypeNewPIN.setEchoChar((char) 0);
        } else {
            EyeButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png")));
            txtRetypeNewPIN.setEchoChar('*');
        }
    }

    private void btnRequestExtendActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbDuration.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a duration to extend", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String duration = cbDuration.getSelectedItem().toString();
        
        // Update Status to "Request Extend + (duration)" 
        String newStatus = "Request Extend (" + duration + ")";

        if (new ReaderCardDAO().updateCardStatus(cardID, newStatus)) {
            JOptionPane.showMessageDialog(null, "Request to extend has been sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableReaderCard();

            new RequestDAO().insertRequest(cardID + " has request to EXTEND READER CARD");

            try {
                new Reader().extendReaderCardRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to send request to extend", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCancelRequestActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblBookRequest.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a request to cancel", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 

        // Only status = "Requested" can be canceled
        if (!tblBookRequest.getValueAt(selectedRow, 3).equals("Requested")) {
            JOptionPane.showMessageDialog(null, "Only request with status 'Requested' can be canceled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int requestID = Integer.parseInt(tblBookRequest.getValueAt(selectedRow, 0).toString());

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this request?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (new BookRequestDAO().cancelRequest(requestID)) {
                JOptionPane.showMessageDialog(null, "Request has been canceled successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTableBookRequest();

                new RequestDAO().insertRequest(cardID + " has CANCELED BOOK REQUEST ");

                try {
                    new Reader().cancelBookRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to cancel request", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UserRequest(new Home(), null).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel BookRequestPanel;
    private javax.swing.JButton EyeButton;
    private javax.swing.JButton EyeButton1;
    private javax.swing.JButton EyeButton2;
    private javax.swing.JPanel ReaderCardPanel;
    private javax.swing.JScrollPane TableScrollPanel;
    private javax.swing.JButton btnCancelRequest;
    private javax.swing.JButton btnEditPIN;
    private javax.swing.JButton btnRequestExtend;
    private javax.swing.JComboBox<String> cbDuration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBookRequest;
    private javax.swing.JTable tblReaderCard;
    private javax.swing.JPasswordField txtNewPIN;
    private javax.swing.JPasswordField txtOldPIN;
    private javax.swing.JPasswordField txtRetypeNewPIN;
    // End of variables declaration//GEN-END:variables
}
