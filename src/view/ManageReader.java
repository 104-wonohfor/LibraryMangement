/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import DAO.BookRequestDAO;
import DAO.BookshelfDAO;
import DAO.ReaderCardDAO;
import DAO.ReaderDAO;
import model.Reader;

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

import javax.swing.JTextField;

import java.time.LocalDate;

/**
 *
 * @author Thang
 */
public class ManageReader extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    
    public ManageReader(Home home) {
        setTitle("Manage Reader");
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
        txtName.setEnabled(value);
        dcDOB.setEnabled(value);
        txtAddress.setEnabled(value);
        txtGender.setEnabled(value);
        txtPhoneNum.setEnabled(value);
        txtEmail.setEnabled(value);
        txtIDNumber.setEnabled(value);
        txtCardID.setEnabled(value);
    }

    private void setFieldsDataEmpty() {
        txtName.setText("");
        dcDOB.setDate(null);    
        txtAddress.setText("");
        txtGender.setText("");
        txtPhoneNum.setText("");
        txtEmail.setText("");
        txtIDNumber.setText("");
        txtCardID.setText("");
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
        loadTableReader();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblReader.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }

    private void loadTableReader() {
        List<Reader> readers = new ReaderDAO().getAllReaders();

        String[][] data = new String[readers.size()][9];

        for (int i = 0; i < readers.size(); i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = readers.get(i).getName();
            data[i][2] = readers.get(i).getDob();
            data[i][3] = readers.get(i).getAddress();
            data[i][4] = readers.get(i).getGender();
            data[i][5] = readers.get(i).getPhoneNumber();
            data[i][6] = readers.get(i).getEmail();
            data[i][7] = readers.get(i).getIdNumber();
            data[i][8] = readers.get(i).getCardID();
        }

        tblReader.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "No.", "Name", "DOB", "Address", "Gender", "Phone Number", "Email", "ID Number", "Card ID"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột
        tblReader.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblReader.getColumnModel().getColumn(1).setPreferredWidth(130); // Name
        tblReader.getColumnModel().getColumn(2).setPreferredWidth(100); // DOB
        tblReader.getColumnModel().getColumn(3).setPreferredWidth(100); // Address
        tblReader.getColumnModel().getColumn(4).setPreferredWidth(60); // Gender
        tblReader.getColumnModel().getColumn(5).setPreferredWidth(100); // Phone Number
        tblReader.getColumnModel().getColumn(6).setPreferredWidth(180); // Email
        tblReader.getColumnModel().getColumn(7).setPreferredWidth(120); // ID Number
        tblReader.getColumnModel().getColumn(8).setPreferredWidth(80); // Card ID

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
        for (int i = 0; i < tblReader.getColumnModel().getColumnCount(); i++) {
            tblReader.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
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
        lblDOB = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPhoneNum = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtIDNumber = new javax.swing.JTextField();
        lblCardID = new javax.swing.JLabel();
        txtCardID = new javax.swing.JTextField();
        lblAction = new javax.swing.JLabel();
        cbAction = new javax.swing.JComboBox<>();
        btnConfirm = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnResetPassword = new javax.swing.JButton();
        dcDOB = new com.toedter.calendar.JDateChooser();
        DataPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbSearchChoice1 = new javax.swing.JComboBox<>();
        TableScrollPanel = new javax.swing.JScrollPane();
        tblReader = new javax.swing.JTable();
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
        lblName.setText("Name");

        lblDOB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDOB.setForeground(new java.awt.Color(215, 19, 19));
        lblDOB.setText("DOB");

        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtName.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(215, 19, 19));
        jLabel23.setText("Address");

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAddress.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtAddress.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(215, 19, 19));
        jLabel26.setText("Gender");
        jLabel26.setToolTipText("");

        txtGender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGender.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtGender.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(215, 19, 19));
        jLabel27.setText("Phone Num");

        txtPhoneNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPhoneNum.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPhoneNum.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(215, 19, 19));
        jLabel28.setText("Email");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtEmail.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(215, 19, 19));
        jLabel29.setText("ID Number");

        txtIDNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIDNumber.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtIDNumber.setEnabled(false);

        lblCardID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCardID.setForeground(new java.awt.Color(215, 19, 19));
        lblCardID.setText("Card ID");

        txtCardID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCardID.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtCardID.setEnabled(false);

        lblAction.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAction.setForeground(new java.awt.Color(215, 19, 19));
        lblAction.setText("Action");

        cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View All", "View", "Edit", "Add" }));
        cbAction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbActionItemStateChanged(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(0, 113, 45));
        btnConfirm.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(255, 251, 230));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(215, 19, 19));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnResetPassword.setBackground(new java.awt.Color(240, 222, 54));
        btnResetPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnResetPassword.setForeground(new java.awt.Color(215, 19, 19));
        btnResetPassword.setText("Reset Password");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

        dcDOB.setDateFormatString("yyyy-MM-dd\n");
        dcDOB.setEnabled(false);
        dcDOB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout InforPanel1Layout = new javax.swing.GroupLayout(InforPanel1);
        InforPanel1.setLayout(InforPanel1Layout);
        InforPanel1Layout.setHorizontalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InforPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
                .addGap(40, 40, 40)
                .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbAction, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        InforPanel1Layout.setVerticalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InforPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        DataPanel.setBackground(new java.awt.Color(13, 18, 130));
        DataPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(215, 19, 19));
        jLabel1.setText("Search By");

        cbSearchChoice1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "Phone Number", "ID Number", "Card ID" }));

        tblReader.setModel(new javax.swing.table.DefaultTableModel(
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
        tblReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblReaderMousePressed(evt);
            }
        });
        TableScrollPanel.setViewportView(tblReader);

        cbSearchChoice2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "Phone Number", "ID Number", "Card ID" }));

        cbSearchChoice3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "Phone Number", "ID Number", "Card ID" }));

        cbSearchChoice4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "Phone Number", "ID Number", "Card ID" }));

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

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblReader.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a reader to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this reader?", "Delete Reader", JOptionPane.YES_NO_OPTION);

        if (choice == 0) {
            String readerCardId = tblReader.getValueAt(tblReader.getSelectedRow(), 8).toString();

            // Kiểm tra reader này có đang mượn sách không
            if (new BookRequestDAO().isReaderBorrowing(readerCardId)) {
                JOptionPane.showMessageDialog(null, "This reader is borrowing books. Must return all books before deleting", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (new ReaderDAO().deleteReader(readerCardId)) {
                JOptionPane.showMessageDialog(null, "Delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTableReader();
                setFieldsDataEmpty();
                cbAction.setSelectedItem("View");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblReader.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a reader to reset password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblReader.getSelectedRow();
        String idNumber = tblReader.getValueAt(row, 7).toString();

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset password for this reader?", "Reset Password", JOptionPane.YES_NO_OPTION);

        if (choice == 0) {
            if (new ReaderDAO().resetPassword(idNumber)) {
                JOptionPane.showMessageDialog(null, "Password has been reset to default value: ReaderHUST@2024", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to reset password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean checkFieldsAcceptable() {
        if (txtName.getText().equals("") || txtAddress.getText().equals("") || txtGender.getText().equals("") || 
            txtPhoneNum.getText().equals("") || txtEmail.getText().equals("") || txtIDNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!txtPhoneNum.getText().matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(null, "Phone number must have 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!txtEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!txtIDNumber.getText().matches("^\\d{12}$")) {
            JOptionPane.showMessageDialog(null, "ID number must have 12 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!txtGender.getText().equals("Male") && !txtGender.getText().equals("Female")) {
            JOptionPane.showMessageDialog(null, "Gender must be Male or Female", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } 

        // Kiểm tra ngày sinh
        String selectDate = ((JTextField) dcDOB.getDateEditor().getUiComponent()).getText();
        if (selectDate.equals("")) {
            JOptionPane.showMessageDialog(null, "Please select a DOB", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        switch (cbAction.getSelectedItem().toString()) {
            case "View All":
                JOptionPane.showMessageDialog(null, "Nothing to confirm here", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "View":
                JOptionPane.showMessageDialog(null, "Nothing to confirm here", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Edit":
                if (!checkFieldsAcceptable()) {
                    return;
                }

                // Kiểm tra có trùng Phone Number, Email có bị trùng của người khác không
                if (!txtPhoneNum.getText().equals(new ReaderDAO().getPhoneNumberByIDNumber(txtIDNumber.getText())) && new ReaderDAO().checkPhoneNumber(txtPhoneNum.getText())) {
                    JOptionPane.showMessageDialog(null, "Another Reader has this phone number", "Error", JOptionPane.ERROR_MESSAGE);
                    setTextBasedOnReaderChosen();
                    return;
                } else if (!txtEmail.getText().equals(new ReaderDAO().getEmailByIDNumber(txtIDNumber.getText())) && new ReaderDAO().checkEmail(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(null, "Another Reader has this email", "Error", JOptionPane.ERROR_MESSAGE);
                    setTextBasedOnReaderChosen();
                    return;
                }

                String idNumber = tblReader.getValueAt(tblReader.getSelectedRow(), 7).toString();
                String address = txtAddress.getText();
                String phoneNum = txtPhoneNum.getText();

                if (new ReaderDAO().updateReader(idNumber, address, phoneNum)) {
                    JOptionPane.showMessageDialog(null, "Update successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableReader();
                    cbAction.setSelectedItem("View");
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Add":
                if (!checkFieldsAcceptable()) {
                    return;
                }

                // Kiểm tra có trùng Phone Number, Email, ID Number không
                if (new ReaderDAO().checkPhoneNumber(txtPhoneNum.getText())) {
                    JOptionPane.showMessageDialog(null, "Another Reader has the same phone number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (new ReaderDAO().checkEmail(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(null, "Another Reader has the same email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (new ReaderDAO().checkIDNumber(txtIDNumber.getText())) {
                    JOptionPane.showMessageDialog(null, "Another Reader has the same ID number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name1 = txtName.getText();
                String dob1 = ((JTextField) dcDOB.getDateEditor().getUiComponent()).getText();
                String address1 = txtAddress.getText();
                String gender1 = txtGender.getText();
                String phoneNum1 = txtPhoneNum.getText();
                String email1 = txtEmail.getText();
                String idNumber1 = txtIDNumber.getText();

                String readerCardId = new ReaderCardDAO().getLatestReaderCardID();
                
                // dateIssued là ngày hiện tại
                LocalDate dateIssued = LocalDate.now();
                LocalDate dateExpired = null;

                if (new ReaderCardDAO().addReaderCard(readerCardId, dateIssued, dateExpired) && 
                    new ReaderDAO().addReader(name1, dob1, address1, gender1, phoneNum1, email1, idNumber1, readerCardId)) {
                    JOptionPane.showMessageDialog(null, "Add successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableReader();
                    cbAction.setSelectedItem("View");
                    setFieldsDataEmpty();
                } else {
                    JOptionPane.showMessageDialog(null, "Add failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            default:
                break;
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

        loadTableReaderSearch(search1, search2, search3, search4, 
                                 searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                 searchBoolean1, searchBoolean2, searchBoolean3);

        cbAction.setSelectedIndex(1);
        setFieldsDataEmpty();
    }

    private void loadTableReaderSearch(String search1, String search2, String search3, String search4, 
                                         String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                         String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"No.", "Name", "DOB", "Address","Gender", "Phone Number", "Email", "ID Number", "Card ID"});

        ArrayList<Reader> list = new ReaderDAO().searchReader(search1, search2, search3, search4, 
                                                               searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                                               searchBoolean1, searchBoolean2, searchBoolean3);

        for (int i = 0; i < list.size(); i++) {
            Reader reader = list.get(i);
            model.addRow(new Object[]{i + 1, 
            reader.getName(), 
            reader.getDob(), 
            reader.getAddress(), 
            reader.getGender(), 
            reader.getPhoneNumber(), 
            reader.getEmail(), 
            reader.getIdNumber(), 
            reader.getCardID()});
        }

        tblReader.setModel(model);

        tblReader.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblReader.getColumnModel().getColumn(1).setPreferredWidth(130); // Name
        tblReader.getColumnModel().getColumn(2).setPreferredWidth(100); // DOB
        tblReader.getColumnModel().getColumn(3).setPreferredWidth(100); // Address
        tblReader.getColumnModel().getColumn(4).setPreferredWidth(60); // Gender
        tblReader.getColumnModel().getColumn(5).setPreferredWidth(100); // Phone Number
        tblReader.getColumnModel().getColumn(6).setPreferredWidth(180); // Email
        tblReader.getColumnModel().getColumn(7).setPreferredWidth(120); // ID Number
        tblReader.getColumnModel().getColumn(8).setPreferredWidth(80); // Card ID

        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);

        customizeComponents();
    }

    private void cbActionItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            switch (cbAction.getSelectedItem().toString()) {
                case "View All":
                    loadTableReader();                    
                    setFieldsDataEmpty();
                    setFieldsDataEditable(false);
                    // setFieldsSearchDefault();
                    break;
                case "View":
                    if (tblReader.getSelectedRow() != -1) {
                        setTextBasedOnReaderChosen();
                    }
                    setFieldsDataEditable(false);
                    break;
                case "Edit":
                    if (tblReader.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a row to edit", "Error", JOptionPane.ERROR_MESSAGE);
                        cbAction.setSelectedItem("View");
                        setFieldsDataEmpty();
                    } else {
                        txtAddress.setEnabled(true);
                        txtPhoneNum.setEnabled(true);
                        txtEmail.setEnabled(true);
                    }
                    break;
                case "Add":
                    tblReader.clearSelection();
                    setFieldsDataEditable(true);
                    txtCardID.setEnabled(false);
                    setFieldsDataEmpty();

                    JTextFieldDateEditor editorDOB = (JTextFieldDateEditor) dcDOB.getDateEditor();
                    editorDOB.setEditable(false);

                    break;
                default:
                    break;
            }
        }
    }

    private void setTextBasedOnReaderChosen() {
        int row = tblReader.getSelectedRow();
        String idNumber = tblReader.getValueAt(row, 7).toString();

        Reader reader = new ReaderDAO().getReaderByIDNumber(idNumber);

        txtName.setText(reader.getName());
        dcDOB.setDate(Date.valueOf(reader.getDob()));
        txtAddress.setText(reader.getAddress());
        txtGender.setText(reader.getGender());
        txtPhoneNum.setText(reader.getPhoneNumber());
        txtEmail.setText(reader.getEmail());
        txtIDNumber.setText(reader.getIdNumber());
        txtCardID.setText(reader.getCardID());

        txtName.setCaretPosition(0);
        txtAddress.setCaretPosition(0);
        txtGender.setCaretPosition(0);
        txtPhoneNum.setCaretPosition(0);
        txtEmail.setCaretPosition(0);
        txtIDNumber.setCaretPosition(0);
        txtCardID.setCaretPosition(0);
    }

    private void tblReaderMousePressed(java.awt.event.MouseEvent evt) {
        // make user cannot edit any field when double click on a row
        boolean editing = tblReader.isEditing();
        if (editing) {
            tblReader.getCellEditor().stopCellEditing();
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
            java.util.logging.Logger.getLogger(ManageReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageReader(new Home()).setVisible(true);
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
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbAction;
    private javax.swing.JComboBox<String> cbSearchBoolean1;
    private javax.swing.JComboBox<String> cbSearchBoolean2;
    private javax.swing.JComboBox<String> cbSearchBoolean3;
    private javax.swing.JComboBox<String> cbSearchChoice1;
    private javax.swing.JComboBox<String> cbSearchChoice2;
    private javax.swing.JComboBox<String> cbSearchChoice3;
    private javax.swing.JComboBox<String> cbSearchChoice4;
    private com.toedter.calendar.JDateChooser dcDOB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel lblAction;
    private javax.swing.JLabel lblCardID;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblName;
    private javax.swing.JTable tblReader;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCardID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNum;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    // End of variables declaration//GEN-END:variables
}
