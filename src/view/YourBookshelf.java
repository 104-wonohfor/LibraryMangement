/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import DAO.BookCopyDAO;
import DAO.BookDAO;
import DAO.BookRequestDAO;
import DAO.BookshelfDAO;
import DAO.RequestDAO;
import model.Book;
import model.BookCopy;
import model.Reader;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Thang
 */
public class YourBookshelf extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    private String cardID = "0";
    
    public YourBookshelf(Home home, String cardID) {
        setTitle("Your Bookshelf");
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
        loadTableBookInBookShelf();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblYourBookshelf.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    }

    private void loadTableBookInBookShelf() {
        String listBookCodes = new BookshelfDAO().getListBookCodeByCardID(cardID);
                
        if (listBookCodes == null) {
            btnTickAll.setEnabled(false);
            btnRemoveTick.setEnabled(false);
            btnRemoveBook.setEnabled(false);
            btnRequestBook.setEnabled(false);

            btnTickAll.setVisible(false);
            btnRemoveTick.setVisible(false);
            btnRemoveBook.setVisible(false);
            btnRequestBook.setVisible(false);

            // Clear the table if listBookCodes is null
            tblYourBookshelf.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "Select", "No.", "Book ID", "Code", "Location", "Status", "Title", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes"
                }
            ));

            tblYourBookshelf.getColumnModel().getColumn(0).setPreferredWidth(50); // Select
            tblYourBookshelf.getColumnModel().getColumn(1).setPreferredWidth(50); // No.
            tblYourBookshelf.getColumnModel().getColumn(2).setPreferredWidth(80); // ID
            tblYourBookshelf.getColumnModel().getColumn(3).setPreferredWidth(105); // Code
            tblYourBookshelf.getColumnModel().getColumn(4).setPreferredWidth(230); // Location
            tblYourBookshelf.getColumnModel().getColumn(5).setPreferredWidth(80); // Status
            tblYourBookshelf.getColumnModel().getColumn(6).setPreferredWidth(200); // Title
            tblYourBookshelf.getColumnModel().getColumn(7).setPreferredWidth(90); // Category
            tblYourBookshelf.getColumnModel().getColumn(8).setPreferredWidth(200); // Authors
            tblYourBookshelf.getColumnModel().getColumn(9).setPreferredWidth(100); // Year Published
            tblYourBookshelf.getColumnModel().getColumn(10).setPreferredWidth(150); // Publisher
            tblYourBookshelf.getColumnModel().getColumn(11).setPreferredWidth(150); // Topic
            tblYourBookshelf.getColumnModel().getColumn(12).setPreferredWidth(120); // Description
            tblYourBookshelf.getColumnModel().getColumn(13).setPreferredWidth(200); // Notes
        
            // set scroll bar to the beginning
            TableScrollPanel.getVerticalScrollBar().setValue(0);
            TableScrollPanel.getHorizontalScrollBar().setValue(0);
        
            customizeComponents();
            return; 
        } else {

        }
    
        String[] listBookCode = listBookCodes.split(",\\s*");
    
        List<BookCopy> bookCopies = new ArrayList<>();
    
        for (String bookCode : listBookCode) {
            BookCopy bookCopy = new BookCopyDAO().getBookCopy(bookCode);
            if (bookCopy != null) {
                bookCopies.add(bookCopy);
            }
        }
    
        Object[][] data = new Object[bookCopies.size()][14]; // Changed to Object[][] to include Boolean for checkbox
    
        for (int i = 0; i < bookCopies.size(); i++) {
            BookCopy bookCopy = bookCopies.get(i);
            data[i][0] = false; // Checkbox column
            data[i][1] = String.valueOf(i + 1);
            data[i][2] = bookCopy.getBookID();
            data[i][3] = bookCopy.getCode();
            data[i][4] = bookCopy.getLocation();
            data[i][5] = bookCopy.getStatus();
            data[i][6] = bookCopy.getBookTitle();
            data[i][7] = bookCopy.getBookCategory();
            data[i][8] = bookCopy.getAuthors() != null ? bookCopy.getAuthors().toString().replace("[", "").replace("]", "") : "";
            data[i][9] = String.valueOf(bookCopy.getYearPublished());
            data[i][10] = bookCopy.getBookPublisher();
            data[i][11] = bookCopy.getBookTopic();
            data[i][12] = bookCopy.getBookDescription();
            data[i][13] = bookCopy.getBookNotes();
        }
        
        tblYourBookshelf.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "Select", "No.", "Book ID", "Code", "Location", "Status", "Title", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes"
            }
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Checkbox column
                }
                return String.class;
            }
    
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Only the checkbox column is editable
            }
        });
    
        tblYourBookshelf.getColumnModel().getColumn(0).setPreferredWidth(50); // Select
        tblYourBookshelf.getColumnModel().getColumn(1).setPreferredWidth(50); // No.
        tblYourBookshelf.getColumnModel().getColumn(2).setPreferredWidth(80); // ID
        tblYourBookshelf.getColumnModel().getColumn(3).setPreferredWidth(105); // Code
        tblYourBookshelf.getColumnModel().getColumn(4).setPreferredWidth(230); // Location
        tblYourBookshelf.getColumnModel().getColumn(5).setPreferredWidth(80); // Status
        tblYourBookshelf.getColumnModel().getColumn(6).setPreferredWidth(200); // Title
        tblYourBookshelf.getColumnModel().getColumn(7).setPreferredWidth(90); // Category
        tblYourBookshelf.getColumnModel().getColumn(8).setPreferredWidth(200); // Authors
        tblYourBookshelf.getColumnModel().getColumn(9).setPreferredWidth(100); // Year Published
        tblYourBookshelf.getColumnModel().getColumn(10).setPreferredWidth(150); // Publisher
        tblYourBookshelf.getColumnModel().getColumn(11).setPreferredWidth(150); // Topic
        tblYourBookshelf.getColumnModel().getColumn(12).setPreferredWidth(120); // Description
        tblYourBookshelf.getColumnModel().getColumn(13).setPreferredWidth(200); // Notes
    
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
        for (int i = 0; i < tblYourBookshelf.getColumnModel().getColumnCount(); i++) {
            tblYourBookshelf.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    private void setFieldsDataEmpty() {
        txtTitle.setText("");
        txtID.setText("");
        txtCategory.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
        txtPublisher.setText("");
        txtTopic.setText("");
        txtDescription.setText("");
        txtNotes.setText("");
        txtLocation.setText("");
        txtStatus.setText("");
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
        DataPanel = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPublisher = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtTopic = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextField();
        lblLocation = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        lblBookCode = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TablePanel = new javax.swing.JPanel();
        TableScrollPanel = new javax.swing.JScrollPane();
        tblYourBookshelf = new javax.swing.JTable();
        btnTickAll = new javax.swing.JButton();
        btnRemoveTick = new javax.swing.JButton();
        btnRemoveBook = new javax.swing.JButton();
        btnRequestBook = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/1200px-Logo_Hust.png")));

        BackgroundPanel.setBackground(new java.awt.Color(13, 18, 130));

        DataPanel.setBackground(new java.awt.Color(13, 18, 130));
        DataPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        lblStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(215, 19, 19));
        lblStatus.setText("Status");

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStatus.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtStatus.setEnabled(false);

        txtID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtID.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtID.setEnabled(false);

        txtCategory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCategory.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtCategory.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(215, 19, 19));
        jLabel23.setText("Author(s)");

        txtAuthor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAuthor.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtAuthor.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(215, 19, 19));
        jLabel26.setText("Year");

        txtYear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtYear.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtYear.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(215, 19, 19));
        jLabel27.setText("Publisher");

        txtPublisher.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPublisher.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPublisher.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(215, 19, 19));
        jLabel28.setText("Topic");

        txtTopic.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTopic.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtTopic.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(215, 19, 19));
        jLabel29.setText("Description");

        txtDescription.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescription.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtDescription.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(215, 19, 19));
        jLabel8.setText("Title");

        txtTitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTitle.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtTitle.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(215, 19, 19));
        jLabel31.setText("Notes");

        txtNotes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNotes.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtNotes.setEnabled(false);

        lblLocation.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblLocation.setForeground(new java.awt.Color(215, 19, 19));
        lblLocation.setText("Location");

        txtLocation.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLocation.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtLocation.setEnabled(false);

        lblBookCode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBookCode.setForeground(new java.awt.Color(215, 19, 19));
        lblBookCode.setText("ID");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(215, 19, 19));
        jLabel10.setText("Category");

        tblYourBookshelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblYourBookshelfMousePressed(evt);
            }
        });
        TableScrollPanel.setViewportView(tblYourBookshelf);

        javax.swing.GroupLayout TablePanelLayout = new javax.swing.GroupLayout(TablePanel);
        TablePanel.setLayout(TablePanelLayout);
        TablePanelLayout.setHorizontalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TableScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
        );
        TablePanelLayout.setVerticalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TableScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        btnTickAll.setBackground(new java.awt.Color(0, 153, 0));
        btnTickAll.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnTickAll.setForeground(new java.awt.Color(255, 255, 255));
        btnTickAll.setText("Tick All");
        btnTickAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTickAllActionPerformed(evt);
            }
        });

        btnRemoveTick.setBackground(new java.awt.Color(255, 51, 0));
        btnRemoveTick.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRemoveTick.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveTick.setText("Remove All Ticks");
        btnRemoveTick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTickActionPerformed(evt);
            }
        });

        btnRemoveBook.setBackground(new java.awt.Color(255, 51, 0));
        btnRemoveBook.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRemoveBook.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveBook.setText("Remove Tick Books");
        btnRemoveBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBookActionPerformed(evt);
            }
        });

        btnRequestBook.setBackground(new java.awt.Color(0, 153, 0));
        btnRequestBook.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRequestBook.setForeground(new java.awt.Color(255, 255, 255));
        btnRequestBook.setText("Request Tick Books");
        btnRequestBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataPanelLayout = new javax.swing.GroupLayout(DataPanel);
        DataPanel.setLayout(DataPanelLayout);
        DataPanelLayout.setHorizontalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTitle))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNotes))
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRemoveTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTickAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRequestBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(72, 72, 72))))
        );
        DataPanelLayout.setVerticalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addComponent(btnTickAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveTick, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveBook, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRequestBook, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTickAllActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < tblYourBookshelf.getRowCount(); i++) {
            tblYourBookshelf.setValueAt(true, i, 0);
        }
    }

    private void btnRemoveTickActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < tblYourBookshelf.getRowCount(); i++) {
            tblYourBookshelf.setValueAt(false, i, 0);
        }
    }

    // Lấy các row có checkbox được chọn và xóa chúng ra
    private void btnRemoveBookActionPerformed(java.awt.event.ActionEvent evt) {
        int count = 0;
        for (int i = 0; i < tblYourBookshelf.getRowCount(); i++) {
            if ((Boolean) tblYourBookshelf.getValueAt(i, 0)) {
                count++;
            }
        }
    
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "Please tick the books you want to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the ticked books?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = tblYourBookshelf.getRowCount() - 1; i >= 0; i--) {
                if ((Boolean) tblYourBookshelf.getValueAt(i, 0)) {
                    Object bookCodeObj = tblYourBookshelf.getValueAt(i, 3);
                    if (bookCodeObj != null) {
                        String bookCode = bookCodeObj.toString();
                        new BookshelfDAO().removeBookFromBookshelf(cardID, bookCode);
                    }
                }
            }
    
            loadTableBookInBookShelf();
            setFieldsDataEmpty();
        }
    }

    private String getSelectedBookCodes() {
        StringBuilder listBookCode = new StringBuilder();
        for (int i = 0; i < tblYourBookshelf.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tblYourBookshelf.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                String bookCode = (String) tblYourBookshelf.getValueAt(i, 3); 
                if (listBookCode.length() > 0) {
                    listBookCode.append(", ");
                }
                listBookCode.append(bookCode);
            }
        }
        return listBookCode.toString();
    }

    private void btnRequestBookActionPerformed(java.awt.event.ActionEvent evt) {
        // Lấy các row có checkbox được chọn 
        // Tạo một string ListBookCode chứa các bookCode được chọn, status = "Requested"
        // Kiểm tra từng bookCode trong ListBookCode nếu có 1 bookCode có status != "Available" thì thông báo bookCode đó không thể request
        
        String listBookCode = getSelectedBookCodes();
        String readerCardID = cardID;
        String status = "Requested";
    
        String[] bookCodes = listBookCode.split(",\\s*");
        for (String bookCode : bookCodes) {
            if (!new BookCopyDAO().checkBookStatusAvailable(bookCode)) {
                JOptionPane.showMessageDialog(null, "Book with code " + bookCode + " is not available for request.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
    
        if (new BookRequestDAO().sendBookRequest(readerCardID, listBookCode, status)) {
            JOptionPane.showMessageDialog(null, "Book request sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            new RequestDAO().insertRequest(cardID + " REQUESTED book(s)");

            try {
                new Reader().cancelBookRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to send book request", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    private void setTextBasedOnBookChosen() {
        int selectedRow = tblYourBookshelf.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        String bookID = tblYourBookshelf.getValueAt(selectedRow, 2).toString();

        Book book = new BookDAO().getBookByID(bookID);

        txtTitle.setText(book.getBookTitle());
        txtID.setText(book.getBookID());
        txtCategory.setText(book.getBookCategory());
        txtAuthor.setText(book.getAuthors().toString().replace("[", "").replace("]", ""));
        txtYear.setText(String.valueOf(book.getYearPublished()));
        txtPublisher.setText(book.getBookPublisher());
        txtTopic.setText(book.getBookTopic());
        txtDescription.setText(book.getBookDescription());
        txtNotes.setText(book.getBookNotes());

        txtTitle.setCaretPosition(0);
        txtID.setCaretPosition(0);
        txtCategory.setCaretPosition(0);
        txtAuthor.setCaretPosition(0);
        txtYear.setCaretPosition(0);
        txtPublisher.setCaretPosition(0);
        txtTopic.setCaretPosition(0);
        txtDescription.setCaretPosition(0);
        txtNotes.setCaretPosition(0);

        String BookCode = tblYourBookshelf.getValueAt(selectedRow, 3).toString();
        
        BookCopy bookCopy = new BookCopyDAO().getBookCopyByCode(BookCode);

        txtLocation.setText(bookCopy.getLocation());
        txtStatus.setText(bookCopy.getStatus());
        
        txtID.setText(BookCode);

        txtLocation.setCaretPosition(0);
        txtStatus.setCaretPosition(0);
    }

    private void tblYourBookshelfMousePressed(java.awt.event.MouseEvent evt) {
        setTextBasedOnBookChosen();
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
            java.util.logging.Logger.getLogger(YourBookshelf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YourBookshelf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YourBookshelf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YourBookshelf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new YourBookshelf(new Home(), "0").setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }           
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel DataPanel;
    private javax.swing.JPanel TablePanel;
    private javax.swing.JScrollPane TableScrollPanel;
    private javax.swing.JButton btnRemoveBook;
    private javax.swing.JButton btnRemoveTick;
    private javax.swing.JButton btnRequestBook;
    private javax.swing.JButton btnTickAll;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblBookCode;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblYourBookshelf;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtNotes;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTopic;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
