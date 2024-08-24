/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import DAO.BookCopyDAO;
import DAO.BookRequestDAO;
import DAO.ReaderCardDAO;
import model.BookRequest;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JTextFieldDateEditor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Thang
 */
public class ManageBookRequest extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    
    public ManageBookRequest(Home home) {
        setTitle("Manage Book Request");
        this.home = home;
        initComponents();
        loadDefault();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                home.setEnabled(true);
            }
        });
    }

    private void setFieldsDataEditable(boolean value) {
        txtListBook.setEnabled(value);
        txtCardID.setEnabled(value);
        txtStatus.setEnabled(value);
        dcDateDue.setEnabled(value);
        dcDateBorrowed.setEnabled(value);
        dcDateReturned.setEnabled(value);
        txtPenaltyFee.setEnabled(value);
        txtNote.setEnabled(value);
    }

    private void setFieldsDataEmpty() {
        txtListBook.setText("");
        txtCardID.setText("");
        txtStatus.setText("");
        dcDateDue.setDate(null);
        dcDateBorrowed.setDate(null);
        dcDateReturned.setDate(null);
        txtPenaltyFee.setText("");
        txtNote.setText("");
    }

    private void setFieldsSearchDefault() {
        cbSearchChoice1.setSelectedIndex(0);
        cbSearchChoice2.setSelectedIndex(0);
        cbSearchChoice3.setSelectedIndex(0);
        cbSearchChoice4.setSelectedIndex(0);

        txtSearch1.setText("");
        txtSearch2.setText("");
        txtSearch3.setText("");
        txtSearch4.setText("");

        cbSearchBoolean1.setSelectedIndex(0);
        cbSearchBoolean2.setSelectedIndex(0);
        cbSearchBoolean3.setSelectedIndex(0);
    }
    
    private void loadDefault(){
        loadTableBookRequest();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblBookRequest.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }

    private void loadTableBookRequest() {
        List<BookRequest> bookRequests = new BookRequestDAO().getAllBookRequest();

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
        InforPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblIDNumber = new javax.swing.JLabel();
        txtListBook = new javax.swing.JTextField();
        txtCardID = new javax.swing.JTextField();
        lblCardID = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        lblIssued = new javax.swing.JLabel();
        lblExpired = new javax.swing.JLabel();
        lblAction = new javax.swing.JLabel();
        cbAction = new javax.swing.JComboBox<>();
        btnAcceptRequest = new javax.swing.JButton();
        dcDateDue = new com.toedter.calendar.JDateChooser();
        dcDateBorrowed = new com.toedter.calendar.JDateChooser();
        lblIssued1 = new javax.swing.JLabel();
        lblExpired1 = new javax.swing.JLabel();
        dcDateReturned = new com.toedter.calendar.JDateChooser();
        txtPenaltyFee = new javax.swing.JTextField();
        txtNote = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnRejectRequest = new javax.swing.JButton();
        btnBorrowed = new javax.swing.JButton();
        btnReturned = new javax.swing.JButton();
        DataPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbSearchChoice1 = new javax.swing.JComboBox<>();
        TableScrollPanel = new javax.swing.JScrollPane();
        tblBookRequest = new javax.swing.JTable();
        cbSearchChoice2 = new javax.swing.JComboBox<>();
        cbSearchChoice3 = new javax.swing.JComboBox<>();
        cbSearchChoice4 = new javax.swing.JComboBox<>();
        txtSearch1 = new javax.swing.JTextField();
        txtSearch2 = new javax.swing.JTextField();
        txtSearch3 = new javax.swing.JTextField();
        txtSearch4 = new javax.swing.JTextField();
        cbSearchBoolean1 = new javax.swing.JComboBox<>();
        cbSearchBoolean2 = new javax.swing.JComboBox<>();
        cbSearchBoolean3 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnClearSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/1200px-Logo_Hust.png")));

        BackgroundPanel.setBackground(new java.awt.Color(13, 18, 130));

        InforPanel1.setBackground(new java.awt.Color(13, 18, 130));

        lblName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(215, 19, 19));
        lblName.setText("List Book");

        lblIDNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIDNumber.setForeground(new java.awt.Color(215, 19, 19));
        lblIDNumber.setText("Card ID");
        lblIDNumber.setToolTipText("");

        txtListBook.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtListBook.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtListBook.setEnabled(false);

        txtCardID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCardID.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtCardID.setEnabled(false);

        lblCardID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCardID.setForeground(new java.awt.Color(215, 19, 19));
        lblCardID.setText("Status");

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStatus.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtStatus.setEnabled(false);

        lblStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(215, 19, 19));
        lblStatus.setText("Date Borrowed");
        lblStatus.setToolTipText("");

        lblIssued.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIssued.setForeground(new java.awt.Color(215, 19, 19));
        lblIssued.setText("Date Due");

        lblExpired.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExpired.setForeground(new java.awt.Color(215, 19, 19));
        lblExpired.setText("Date Returned");

        lblAction.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAction.setForeground(new java.awt.Color(215, 19, 19));
        lblAction.setText("Action");

        cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View All", "View", "Add" }));
        cbAction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbActionItemStateChanged(evt);
            }
        });

        btnAcceptRequest.setBackground(new java.awt.Color(0, 153, 0));
        btnAcceptRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAcceptRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnAcceptRequest.setText("Accept Request");
        btnAcceptRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptRequestActionPerformed(evt);
            }
        });

        dcDateDue.setDateFormatString("yyyy-MM-dd\n");
        dcDateDue.setEnabled(false);
        dcDateDue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        dcDateBorrowed.setDateFormatString("yyyy-MM-dd\n");
        dcDateBorrowed.setEnabled(false);
        dcDateBorrowed.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblIssued1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIssued1.setForeground(new java.awt.Color(215, 19, 19));
        lblIssued1.setText("Penalty Fee");

        lblExpired1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExpired1.setForeground(new java.awt.Color(215, 19, 19));
        lblExpired1.setText("Note");

        dcDateReturned.setDateFormatString("yyyy-MM-dd\n");
        dcDateReturned.setEnabled(false);
        dcDateReturned.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPenaltyFee.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPenaltyFee.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPenaltyFee.setEnabled(false);

        txtNote.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNote.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtNote.setEnabled(false);

        btnConfirm.setBackground(new java.awt.Color(240, 222, 54));
        btnConfirm.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(215, 19, 19));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnRejectRequest.setBackground(new java.awt.Color(255, 51, 0));
        btnRejectRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRejectRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnRejectRequest.setText("Reject Request");
        btnRejectRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectRequestActionPerformed(evt);
            }
        });

        btnBorrowed.setBackground(new java.awt.Color(240, 222, 54));
        btnBorrowed.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBorrowed.setForeground(new java.awt.Color(215, 19, 19));
        btnBorrowed.setText("Borrowed");
        btnBorrowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrowedActionPerformed(evt);
            }
        });

        btnReturned.setBackground(new java.awt.Color(240, 222, 54));
        btnReturned.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnReturned.setForeground(new java.awt.Color(215, 19, 19));
        btnReturned.setText("Returned");
        btnReturned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InforPanel1Layout = new javax.swing.GroupLayout(InforPanel1);
        InforPanel1.setLayout(InforPanel1Layout);
        InforPanel1Layout.setHorizontalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InforPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblIssued1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAcceptRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPenaltyFee, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(btnBorrowed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblExpired1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblIssued, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcDateDue, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblExpired, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtListBook, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRejectRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturned, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDateReturned, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDateBorrowed, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAction)
                .addGap(12, 12, 12)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbAction, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        InforPanel1Layout.setVerticalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InforPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcDateBorrowed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(InforPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dcDateReturned, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addComponent(btnRejectRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReturned, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtListBook, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblIssued, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcDateDue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIssued1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExpired1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPenaltyFee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(btnAcceptRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrowed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        DataPanel.setBackground(new java.awt.Color(13, 18, 130));
        DataPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(215, 19, 19));
        jLabel1.setText("Search By");

        cbSearchChoice1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Card ID", "Status", "Note" }));

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
        tblBookRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBookRequestMousePressed(evt);
            }
        });
        TableScrollPanel.setViewportView(tblBookRequest);

        cbSearchChoice2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Card ID", "Status", "Note" }));

        cbSearchChoice3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Card ID", "Status", "Note" }));

        cbSearchChoice4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Card ID", "Status", "Note" }));

        txtSearch1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSearch2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSearch3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSearch4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbSearchBoolean1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        cbSearchBoolean2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        cbSearchBoolean3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_rs.png"))); // NOI18N
        btnSearch.setPreferredSize(new java.awt.Dimension(48, 48));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClearSearch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClearSearch.setForeground(new java.awt.Color(0, 153, 0));
        btnClearSearch.setText("Clear");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataPanelLayout = new javax.swing.GroupLayout(DataPanel);
        DataPanel.setLayout(DataPanelLayout);
        DataPanelLayout.setHorizontalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPanelLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbSearchChoice3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch1)
                            .addComponent(txtSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(txtSearch3)))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addComponent(cbSearchChoice4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSearchBoolean1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSearchBoolean2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSearchBoolean3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jLabel2)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DataPanelLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(841, Short.MAX_VALUE)))
        );
        DataPanelLayout.setVerticalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSearchBoolean1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSearchBoolean2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbSearchChoice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSearchBoolean3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSearchChoice4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(TableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DataPanelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InforPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addComponent(InforPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnBorrowedActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblBookRequest.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to borrow", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!txtStatus.getText().equals("Accepted")) {
            JOptionPane.showMessageDialog(null, "Only request with status 'Accepted' can be borrowed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String requestID = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 0).toString();
        
        if (new BookRequestDAO().updateBookRequestBorrowed(requestID)) {
            JOptionPane.showMessageDialog(null, "Update successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookRequest();
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnReturnedActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblBookRequest.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to return", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!txtStatus.getText().equals("Borrowed")) {
            JOptionPane.showMessageDialog(null, "Only request with status 'Borrowed' can be returned", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String requestID = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 0).toString();
        // date returned is today
        LocalDate dateReturned = LocalDate.now();
        // get penalty fee from txtPenaltyFee
        double penaltyFee = 0.0;
        String penaltyFeeText = txtPenaltyFee.getText();
        if (penaltyFeeText != null && !penaltyFeeText.trim().isEmpty()) {
            penaltyFee = Double.parseDouble(penaltyFeeText.trim());
        }
        
        String note = txtNote.getText();

        
        // List book code at column 1
        String listBookCode = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 1).toString();
        String[] bookCodeList = listBookCode.split(",\\s*");

        // Update status of book copy to "Available"
        for (String bookCode : bookCodeList) {
            new BookCopyDAO().updateBookStatus(bookCode, "Available");
        }

        if (new BookRequestDAO().updateBookRequestReturned(requestID, dateReturned, penaltyFee, note)) {
            JOptionPane.showMessageDialog(null, "Update successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookRequest();
            setFieldsDataEditable(false);
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        switch (cbAction.getSelectedItem().toString()) {
            case "View":
                JOptionPane.showMessageDialog(null, "Nothing to confirm :((", "Error", JOptionPane.ERROR_MESSAGE);
                break;

            case "View All":
                JOptionPane.showMessageDialog(null, "Nothing to confirm :((", "Error", JOptionPane.ERROR_MESSAGE);
                break;

            case "Add":
                String listBookCode2 = txtListBook.getText();
                String cardID2 = txtCardID.getText();
                String note2 = txtNote.getText();
                if (note2 == null || note2.trim().isEmpty()) {
                    note2 = "No Notes";
                }

                // if listBookCode, cardID, dateBorrowed, dateDue are empty, Announce to user
                if (listBookCode2.isEmpty() || cardID2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in List Book, Card ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // get list book code at txtListBook, listBookCode = "K2.VQ.2014, K2.VQ.2014.C2, K2.VQ.2014.C3"
                String listBookCode3 = txtListBook.getText();

                // Tách danh sách mã sách thành một mảng các mã sách riêng lẻ
                String[] bookCodeList = listBookCode3.split(",\\s*");

                // Kiểm tra từng book code status có là "Available" không
                for (String bookCode : bookCodeList) {
                    if (!new BookCopyDAO().checkBookStatusAvailable(bookCode)) {
                        JOptionPane.showMessageDialog(null, "Book code " + bookCode + " is not available or not exist", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Kiểm tra cardID có tồn tại không
                if (!new ReaderCardDAO().checkCardIDExist(cardID2)) {
                    JOptionPane.showMessageDialog(null, "Card ID " + cardID2 + " is not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                if (new BookRequestDAO().addBookRequest(listBookCode2, cardID2, "Requested", note2)) {
                    JOptionPane.showMessageDialog(null, "Add request successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableBookRequest();
                    setFieldsDataEmpty();
                } else {
                    JOptionPane.showMessageDialog(null, "Add request failed", "Error", JOptionPane.ERROR_MESSAGE);
                }                
        }
    }

    private void btnAcceptRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblBookRequest.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to accept request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtStatus.getText().equals("Requested")) {
            JOptionPane.showMessageDialog(null, "Only request with status 'Requested' can be accepted", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // get list book code at column 1, listBookCode = "K2.VQ.2014, K2.VQ.2014.C2, K2.VQ.2014.C3"
        String listBookCode = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 1).toString();

        // Tách danh sách mã sách thành một mảng các mã sách riêng lẻ
        String[] bookCodeList = listBookCode.split(",\\s*");

        // Kiểm tra từng book code status có là "Available" không
        for (String bookCode : bookCodeList) {
            if (!new BookCopyDAO().checkBookStatusAvailable(bookCode)) {
                JOptionPane.showMessageDialog(null, "Book code " + bookCode + " is not available", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String requestID = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 0).toString();
        // Lấy dateBorrowed là ngày hôm nay
        LocalDate dateBorrowed = LocalDate.now();
        // DateDue là ngày hôm nay + 60 ngày
        LocalDate dateDue = dateBorrowed.plus(60, ChronoUnit.DAYS);

        if (new BookRequestDAO().acceptBookRequest(requestID, "Accepted", dateBorrowed, dateDue)) {
            // Cập nhật trạng thái trong bookCodeList là "Borrowed"
            for (String bookCode : bookCodeList) {
                new BookCopyDAO().updateBookStatus(bookCode, "Borrowed");
            }
            JOptionPane.showMessageDialog(null, "Accept request successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookRequest();
            setFieldsDataEmpty();

        } else {
            JOptionPane.showMessageDialog(null, "Accept request failed", "Error", JOptionPane.ERROR_MESSAGE);
        }         
    }

    private void btnRejectRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblBookRequest.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to reject request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtStatus.getText().equals("Requested")) {
            JOptionPane.showMessageDialog(null, "Only request with status 'Requested' can be rejected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String requestID = tblBookRequest.getValueAt(tblBookRequest.getSelectedRow(), 0).toString();

        if (new BookRequestDAO().rejectBookRequest(requestID, "Rejected")) {
            JOptionPane.showMessageDialog(null, "Reject request successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookRequest();
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Reject request failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {
        setFieldsSearchDefault();    
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String search1 = txtSearch1.getText();
        String search2 = txtSearch2.getText();
        String search3 = txtSearch3.getText();
        String search4 = txtSearch4.getText();

        String searchChoice1 = cbSearchChoice1.getSelectedItem().toString();
        String searchChoice2 = cbSearchChoice2.getSelectedItem().toString();
        String searchChoice3 = cbSearchChoice3.getSelectedItem().toString();
        String searchChoice4 = cbSearchChoice4.getSelectedItem().toString();

        String searchBoolean1 = cbSearchBoolean1.getSelectedItem().toString();
        String searchBoolean2 = cbSearchBoolean2.getSelectedItem().toString();
        String searchBoolean3 = cbSearchBoolean3.getSelectedItem().toString();

        // if 4 search CHOICE fields are "Any Field" or 4 search TEXT fields are empty, Announce to user
        if ((search1.equals("") && search2.equals("") && search3.equals("") && search4.equals(""))
                || (searchChoice1.equals("Any Field") && searchChoice2.equals("Any Field") && searchChoice3.equals("Any Field") && searchChoice4.equals("Any Field"))) {
            JOptionPane.showMessageDialog(null, "Please enter at least 1 search field", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loadTableBookRequestSearch(search1, search2, search3, search4, 
                                 searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                 searchBoolean1, searchBoolean2, searchBoolean3);

        cbAction.setSelectedIndex(1);
        setFieldsDataEmpty();
    }

    private void loadTableBookRequestSearch(String search1, String search2, String search3, String search4, 
                                         String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                         String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Request ID", "Book Code", "Card ID", "Status", "Borrow Date", "Due Date", "Return Date", "Penalty Fee", "Note"});

        ArrayList<BookRequest> list = new BookRequestDAO().searchBookRequest(search1, search2, search3, search4, 
                                                               searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                                               searchBoolean1, searchBoolean2, searchBoolean3);
                                                            
        for (int i = 0; i < list.size(); i++) {
            BookRequest bookRequest = list.get(i);
            model.addRow(new Object[]{bookRequest.getBookRequestID(),
            bookRequest.getBookCode().toString().replace("[", "").replace("]", ""), 
            bookRequest.getCardID(), 
            bookRequest.getStatus(), 
            bookRequest.getBorrowDate(), 
            bookRequest.getDueDate(), 
            bookRequest.getReturnDate(), 
            bookRequest.getPenaltyFee(), 
            bookRequest.getNote()});
        }

        tblBookRequest.setModel(model);

        tblBookRequest.getColumnModel().getColumn(0).setPreferredWidth(80); // Request ID
        tblBookRequest.getColumnModel().getColumn(1).setPreferredWidth(200); // Book Code
        tblBookRequest.getColumnModel().getColumn(2).setPreferredWidth(100); // Card ID
        tblBookRequest.getColumnModel().getColumn(3).setPreferredWidth(100); // Status
        tblBookRequest.getColumnModel().getColumn(4).setPreferredWidth(100); // Borrow Date
        tblBookRequest.getColumnModel().getColumn(5).setPreferredWidth(100); // Due Date
        tblBookRequest.getColumnModel().getColumn(6).setPreferredWidth(100); // Return Date
        tblBookRequest.getColumnModel().getColumn(7).setPreferredWidth(100); // Penalty Fee
        tblBookRequest.getColumnModel().getColumn(8).setPreferredWidth(150); // Note

        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);

        customizeComponents();
    }

    private void cbActionItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            switch (cbAction.getSelectedItem().toString()) {
                case "View All":
                    loadTableBookRequest();               
                    setFieldsDataEmpty();
                    setFieldsDataEditable(false);
                    // setFieldsSearchDefault();
                    break;

                case "View":
                    if (tblBookRequest.getSelectedRow() != -1) {
                        setTextBasedOnReaderChosen();
                    }
                    setFieldsDataEditable(false);
                    break;

                case "Add":
                    tblBookRequest.clearSelection();
                    setFieldsDataEditable(true);
                    setFieldsDataEmpty();

                    txtStatus.setEnabled(false);
                    dcDateBorrowed.setEnabled(false);
                    dcDateDue.setEnabled(false);
                    dcDateReturned.setEnabled(false);
                    txtPenaltyFee.setEnabled(false);

                    // disable editing by texting fields (dcDateDue, dcDateBorrowed, dcDateReturned)
                    JTextFieldDateEditor editorDue = (JTextFieldDateEditor) dcDateDue.getDateEditor();
                    editorDue.setEditable(false);

                    JTextFieldDateEditor editorBorrowed = (JTextFieldDateEditor) dcDateBorrowed.getDateEditor();
                    editorBorrowed.setEditable(false);

                    JTextFieldDateEditor editorReturned = (JTextFieldDateEditor) dcDateReturned.getDateEditor();
                    editorReturned.setEditable(false);
                    break;
                default:
                    break;
            }
        }
    }

    private void setTextBasedOnReaderChosen() {
        int row = tblBookRequest.getSelectedRow();
        String requestID = tblBookRequest.getValueAt(row, 0).toString();
        
        BookRequest bookRequest = new BookRequestDAO().getBookRequestByID(requestID);
        
        txtListBook.setText(bookRequest.getBookCode().toString().replace("[", "").replace("]", ""));
        txtCardID.setText(bookRequest.getCardID());
        txtStatus.setText(bookRequest.getStatus());
        
        if (bookRequest.getDueDate() != null) {
            dcDateDue.setDate(Date.valueOf(bookRequest.getDueDate()));
        } else {
            dcDateDue.setDate(null);
        }
        
        if (bookRequest.getBorrowDate() != null) {
            dcDateBorrowed.setDate(Date.valueOf(bookRequest.getBorrowDate()));
        } else {
            dcDateBorrowed.setDate(null);
        }
        
        if (bookRequest.getReturnDate() != null) {
            dcDateReturned.setDate(Date.valueOf(bookRequest.getReturnDate()));
        } else {
            dcDateReturned.setDate(null);
        }
        
        txtPenaltyFee.setText(String.valueOf(bookRequest.getPenaltyFee()));
        txtNote.setText(bookRequest.getNote());
        
        txtListBook.setCaretPosition(0);
        txtCardID.setCaretPosition(0);
        txtStatus.setCaretPosition(0);
        txtPenaltyFee.setCaretPosition(0);
        txtNote.setCaretPosition(0);

        // if status is "Borrowed", enable txtPenaltyFee and txtNote
        if (txtStatus.getText().equals("Borrowed")) {
            txtPenaltyFee.setEnabled(true);
            txtNote.setEnabled(true);
        }
    }

    private void tblBookRequestMousePressed(java.awt.event.MouseEvent evt) {
        // make user cannot edit any field when double click on a row
        boolean editing = tblBookRequest.isEditing();
        if (editing) {
            tblBookRequest.getCellEditor().stopCellEditing();
        }

        // set cbAction to "View" when user click on a row
        cbAction.setSelectedItem("View");

        // set text fields based on the row user clicked
        setTextBasedOnReaderChosen();
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
            java.util.logging.Logger.getLogger(ManageBookRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBookRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBookRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBookRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageBookRequest(new Home()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel DataPanel;
    private javax.swing.JPanel InforPanel1;
    private javax.swing.JScrollPane TableScrollPanel;
    private javax.swing.JButton btnAcceptRequest;
    private javax.swing.JButton btnBorrowed;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnRejectRequest;
    private javax.swing.JButton btnReturned;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbAction;
    private javax.swing.JComboBox<String> cbSearchBoolean1;
    private javax.swing.JComboBox<String> cbSearchBoolean2;
    private javax.swing.JComboBox<String> cbSearchBoolean3;
    private javax.swing.JComboBox<String> cbSearchChoice1;
    private javax.swing.JComboBox<String> cbSearchChoice2;
    private javax.swing.JComboBox<String> cbSearchChoice3;
    private javax.swing.JComboBox<String> cbSearchChoice4;
    private com.toedter.calendar.JDateChooser dcDateBorrowed;
    private com.toedter.calendar.JDateChooser dcDateDue;
    private com.toedter.calendar.JDateChooser dcDateReturned;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAction;
    private javax.swing.JLabel lblCardID;
    private javax.swing.JLabel lblExpired;
    private javax.swing.JLabel lblExpired1;
    private javax.swing.JLabel lblIDNumber;
    private javax.swing.JLabel lblIssued;
    private javax.swing.JLabel lblIssued1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblBookRequest;
    private javax.swing.JTextField txtCardID;
    private javax.swing.JTextField txtListBook;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPenaltyFee;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
