/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import DAO.BookshelfDAO;
import DAO.ReaderCardDAO;
import model.ReaderCard;

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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Thang
 */
public class ManageReaderCard extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    
    public ManageReaderCard(Home home) {
        setTitle("Manage Reader Card");
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
        txtIDNumber.setEnabled(value);
        txtCardID.setEnabled(value);
        txtStatus.setEnabled(value);
        dcIssued.setEnabled(value);
        dcExpired.setEnabled(value);
    }

    private void setFieldsDataEmpty() {
        txtName.setText("");
        txtIDNumber.setText("");
        txtCardID.setText("");
        txtStatus.setText("");
        dcIssued.setDate(null);
        dcExpired.setDate(null);
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
        loadTableReaderCard();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblReader.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }

    private void loadTableReaderCard() {
        List<ReaderCard> readerCards = new ReaderCardDAO().getAllReaderCards();

        String[][] data = new String[readerCards.size()][7];

        for (int i = 0; i < readerCards.size(); i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = readerCards.get(i).getName();
            data[i][2] = readerCards.get(i).getIdNumber();
            data[i][3] = readerCards.get(i).getCardID();
            data[i][4] = readerCards.get(i).getCardStatus();
            data[i][5] = readerCards.get(i).getDateIssued();
            data[i][6] = readerCards.get(i).getDateExpired();
        }

        tblReader.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "No.", "Name", "ID Number", "Card ID", "Status", "Issued", "Expired"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột
        tblReader.getColumnModel().getColumn(0).setPreferredWidth(60); // No.
        tblReader.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        tblReader.getColumnModel().getColumn(2).setPreferredWidth(180); // ID Number
        tblReader.getColumnModel().getColumn(3).setPreferredWidth(120); // Card ID
        tblReader.getColumnModel().getColumn(4).setPreferredWidth(160); // Status
        tblReader.getColumnModel().getColumn(5).setPreferredWidth(100); // Issued
        tblReader.getColumnModel().getColumn(6).setPreferredWidth(100); // Expired

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
        lblIDNumber = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtIDNumber = new javax.swing.JTextField();
        lblCardID = new javax.swing.JLabel();
        txtCardID = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblIssued = new javax.swing.JLabel();
        lblExpired = new javax.swing.JLabel();
        lblAction = new javax.swing.JLabel();
        cbAction = new javax.swing.JComboBox<>();
        dcIssued = new com.toedter.calendar.JDateChooser();
        dcExpired = new com.toedter.calendar.JDateChooser();
        btnAcceptRequest = new javax.swing.JButton();
        btnRejectRequest = new javax.swing.JButton();
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

        lblIDNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIDNumber.setForeground(new java.awt.Color(215, 19, 19));
        lblIDNumber.setText("ID Number");

        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtName.setEnabled(false);

        txtIDNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIDNumber.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtIDNumber.setEnabled(false);

        lblCardID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCardID.setForeground(new java.awt.Color(215, 19, 19));
        lblCardID.setText("Card ID");

        txtCardID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCardID.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtCardID.setEnabled(false);

        lblStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(215, 19, 19));
        lblStatus.setText("Status");
        lblStatus.setToolTipText("");

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStatus.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtStatus.setEnabled(false);

        lblIssued.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIssued.setForeground(new java.awt.Color(215, 19, 19));
        lblIssued.setText("Issued");

        lblExpired.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExpired.setForeground(new java.awt.Color(215, 19, 19));
        lblExpired.setText("Expired");

        lblAction.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAction.setForeground(new java.awt.Color(215, 19, 19));
        lblAction.setText("Action");

        cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View All", "View" }));
        cbAction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbActionItemStateChanged(evt);
            }
        });

        dcIssued.setDateFormatString("yyyy-MM-dd\n");
        dcIssued.setEnabled(false);
        dcIssued.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        dcExpired.setDateFormatString("yyyy-MM-dd\n");
        dcExpired.setEnabled(false);
        dcExpired.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnAcceptRequest.setBackground(new java.awt.Color(0, 153, 0));
        btnAcceptRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAcceptRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnAcceptRequest.setText("Accept Request");
        btnAcceptRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptRequestActionPerformed(evt);
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

        javax.swing.GroupLayout InforPanel1Layout = new javax.swing.GroupLayout(InforPanel1);
        InforPanel1.setLayout(InforPanel1Layout);
        InforPanel1Layout.setHorizontalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InforPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createSequentialGroup()
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InforPanel1Layout.createSequentialGroup()
                                .addComponent(lblIssued, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAcceptRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcIssued, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcExpired, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStatus)
                            .addComponent(btnRejectRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        InforPanel1Layout.setVerticalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InforPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIssued, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dcIssued, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcceptRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRejectRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        DataPanel.setBackground(new java.awt.Color(13, 18, 130));
        DataPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(215, 19, 19));
        jLabel1.setText("Search By");

        cbSearchChoice1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "ID Number", "Card ID", "Status" }));

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
        cbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "ID Number", "Card ID", "Status" }));

        cbSearchChoice3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "ID Number", "Card ID", "Status" }));

        cbSearchChoice4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "Name", "ID Number", "Card ID", "Status" }));

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

    private void acceptRequest() {
        int row = tblReader.getSelectedRow();
        String cardID = tblReader.getValueAt(row, 3).toString();
        String dateIssued = ((JTextField) dcIssued.getDateEditor().getUiComponent()).getText().trim();
        String dateExpired = ((JTextField) dcExpired.getDateEditor().getUiComponent()).getText().trim();

        // if dateIssued or dateExpired is empty, Announce to user
            if (dateIssued.equals("") || dateExpired.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Expired date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lấy ra duration giữa dateIssued và dateExpired. Lấy ngày hôm nay là dateIssued mới, dateExpired mới là ngày hôm nay + duration
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate issuedDate = LocalDate.parse(dateIssued, formatter);
        LocalDate expiredDate = LocalDate.parse(dateExpired, formatter);
        long duration = ChronoUnit.DAYS.between(issuedDate, expiredDate);

        LocalDate newDateIssued = LocalDate.now();
        LocalDate newDateExpired = newDateIssued.plusDays(duration);

        String newDateIssuedStr = newDateIssued.format(formatter);
        String newDateExpiredStr = newDateExpired.format(formatter);

        if (new ReaderCardDAO().acceptRequest(cardID, newDateIssuedStr, newDateExpiredStr) && new BookshelfDAO().addBookshelf(cardID)) {
            JOptionPane.showMessageDialog(null, "Accept request successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableReaderCard();
            setFieldsDataEmpty();

            dcIssued.setEnabled(false);
            dcExpired.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Accept request failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acceptRequestExtend() {
        int row = tblReader.getSelectedRow();
        String cardID = tblReader.getValueAt(row, 3).toString();
        
        if (new ReaderCardDAO().acceptRequestExtend(cardID)) {
            JOptionPane.showMessageDialog(null, "Accept request extend successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableReaderCard();
            setFieldsDataEmpty();

            dcIssued.setEnabled(false);
            dcExpired.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Accept request extend failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAcceptRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblReader.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please choose a reader card to accept request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!txtStatus.getText().equals("Requested") && !txtStatus.getText().contains("Request Extend")) {
            JOptionPane.showMessageDialog(null, "Only reader card with status 'Requested' or 'Request Extend' can be accepted", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtStatus.getText().equals("Requested")) {
            acceptRequest();
        } else if (txtStatus.getText().contains("Request Extend")) {
            acceptRequestExtend();
        }
    }

    private void btnRejectRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblReader.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please choose a reader card to reject request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!txtStatus.getText().equals("Requested")) {
            JOptionPane.showMessageDialog(null, "Only reader card with status 'Requested' can be rejected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblReader.getSelectedRow();
        String cardID = tblReader.getValueAt(row, 3).toString();

        if (new ReaderCardDAO().rejectRequest(cardID)) {
            JOptionPane.showMessageDialog(null, "Reject request successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableReaderCard();
            setFieldsDataEmpty();

            dcIssued.setEnabled(false);
            dcExpired.setEnabled(false);
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
        model.setColumnIdentifiers(new Object[]{"No.", "Name", "ID Number", "Card ID", "Status", "Issued", "Expired"});

        ArrayList<ReaderCard> list = new ReaderCardDAO().searchReaderCard(search1, search2, search3, search4, 
                                                               searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                                               searchBoolean1, searchBoolean2, searchBoolean3);

        for (int i = 0; i < list.size(); i++) {
            ReaderCard readerCard = list.get(i);
            model.addRow(new Object[]{i + 1, 
            readerCard.getName(), 
            readerCard.getIdNumber(), 
            readerCard.getCardID(), 
            readerCard.getCardStatus(), 
            readerCard.getDateIssued(), 
            readerCard.getDateExpired()});
        }

        tblReader.setModel(model);

        tblReader.getColumnModel().getColumn(0).setPreferredWidth(60); // No.
        tblReader.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        tblReader.getColumnModel().getColumn(2).setPreferredWidth(180); // ID Number
        tblReader.getColumnModel().getColumn(3).setPreferredWidth(120); // Card ID
        tblReader.getColumnModel().getColumn(4).setPreferredWidth(160); // Status
        tblReader.getColumnModel().getColumn(5).setPreferredWidth(100); // Issued
        tblReader.getColumnModel().getColumn(6).setPreferredWidth(100); // Expired

        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);

        customizeComponents();
    }

    private void cbActionItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            switch (cbAction.getSelectedItem().toString()) {
                case "View All":
                    loadTableReaderCard();                
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

                case "Add":
                    tblReader.clearSelection();
                    setFieldsDataEditable(true);
                    setFieldsDataEmpty();

                    JTextFieldDateEditor editorIssued = (JTextFieldDateEditor) dcIssued.getDateEditor();
                    editorIssued.setEditable(false);

                    JTextFieldDateEditor editorExpired = (JTextFieldDateEditor) dcExpired.getDateEditor();
                    editorExpired.setEditable(false);
                    break;
                default:
                    break;
            }
        }
    }

    private void setTextBasedOnReaderChosen() {
        int row = tblReader.getSelectedRow();
        String cardID = tblReader.getValueAt(row, 3).toString();

        ReaderCard readerCard = new ReaderCardDAO().getReaderCardByCardID(cardID);

        txtName.setText(readerCard.getName());
        txtIDNumber.setText(readerCard.getIdNumber());
        txtCardID.setText(readerCard.getCardID());
        txtStatus.setText(readerCard.getCardStatus());

        // dcIssued and dcExpired can be null
        // if status is requested and dateIssued and dateExpired are empty, enable dcIssued and dcExpired
        if (readerCard.getDateIssued() != null) {
            dcIssued.setDate(Date.valueOf(readerCard.getDateIssued()));
            dcIssued.setEnabled(false);
        } else {
            dcIssued.setDate(null);
        }

        if (readerCard.getDateExpired() != null) {
            dcExpired.setDate(Date.valueOf(readerCard.getDateExpired()));
            dcExpired.setEnabled(false);
        } else {
            dcExpired.setDate(null);
            dcExpired.setEnabled(true);
        }


        txtName.setCaretPosition(0);
        txtIDNumber.setCaretPosition(0);
        txtCardID.setCaretPosition(0);
        txtStatus.setCaretPosition(0);

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
            java.util.logging.Logger.getLogger(ManageReaderCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageReaderCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageReaderCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageReaderCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageReaderCard(new Home()).setVisible(true);
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
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnRejectRequest;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbAction;
    private javax.swing.JComboBox<String> cbSearchBoolean1;
    private javax.swing.JComboBox<String> cbSearchBoolean2;
    private javax.swing.JComboBox<String> cbSearchBoolean3;
    private javax.swing.JComboBox<String> cbSearchChoice1;
    private javax.swing.JComboBox<String> cbSearchChoice2;
    private javax.swing.JComboBox<String> cbSearchChoice3;
    private javax.swing.JComboBox<String> cbSearchChoice4;
    private com.toedter.calendar.JDateChooser dcExpired;
    private com.toedter.calendar.JDateChooser dcIssued;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAction;
    private javax.swing.JLabel lblCardID;
    private javax.swing.JLabel lblExpired;
    private javax.swing.JLabel lblIDNumber;
    private javax.swing.JLabel lblIssued;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblReader;
    private javax.swing.JTextField txtCardID;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
