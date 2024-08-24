/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.BookCopyDAO;
import DAO.BookDAO;
import model.Book;
import model.BookCopy;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thang
 */
public class ManageBook extends javax.swing.JFrame {

    /**
     * Creates new form ManageBook4
     */
    private Home home;
    
    public ManageBook(Home home) {
        setTitle("Manage Books");
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
        txtID.setEnabled(value);
        txtTitle.setEnabled(value);
        txtCategory.setEnabled(value);
        txtAuthor.setEnabled(value);
        txtYear.setEnabled(value);
        txtPublisher.setEnabled(value);
        txtTopic.setEnabled(value);
        txtDescription.setEnabled(value);
        txtNotes.setEnabled(value);
        txtQuantity.setEnabled(value);
        txtLocation.setEnabled(value);
        txtStatus.setEnabled(value);
    }

    private void setFieldsDataEmpty() {
        txtID.setText("");
        txtTitle.setText("");
        txtCategory.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
        txtPublisher.setText("");
        txtTopic.setText("");
        txtDescription.setText("");
        txtNotes.setText("");
        txtQuantity.setText("");
        txtLocation.setText("");
        txtStatus.setText("");
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
        loadTableBook();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TableScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblBook.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        lblLocation.setVisible(false);
        txtLocation.setVisible(false);
        lblStatus.setVisible(false);
        txtStatus.setVisible(false);

        btnAddBookCopy.setVisible(false);
        btnAddBookCopy.setEnabled(false);
    }

    private void loadTableBook() {
        List<Book> books = new BookDAO().getAllBooks();
    
        String[][] data = new String[books.size()][11]; 
    
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = String.valueOf(i + 1); 
            data[i][1] = books.get(i).getBookID();
            data[i][2] = books.get(i).getBookTitle();
            data[i][3] = books.get(i).getBookCategory();
            data[i][4] = books.get(i).getAuthors().toString().replace("[", "").replace("]", "");
            data[i][5] = String.valueOf(books.get(i).getYearPublished());
            data[i][6] = books.get(i).getBookPublisher();
            data[i][7] = books.get(i).getBookTopic();
            data[i][8] = books.get(i).getBookDescription();
            data[i][9] = books.get(i).getBookNotes();
            data[i][10] = String.valueOf(books.get(i).getBookQuantity());
        }
    
        tblBook.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "No.", "ID", "Title", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes", "Quantity"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột
        tblBook.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblBook.getColumnModel().getColumn(1).setPreferredWidth(80); // ID
        tblBook.getColumnModel().getColumn(2).setPreferredWidth(200); // Title
        tblBook.getColumnModel().getColumn(3).setPreferredWidth(90); // Category
        tblBook.getColumnModel().getColumn(4).setPreferredWidth(200); // Authors
        tblBook.getColumnModel().getColumn(5).setPreferredWidth(100); // Year Published
        tblBook.getColumnModel().getColumn(6).setPreferredWidth(150); // Publisher
        tblBook.getColumnModel().getColumn(7).setPreferredWidth(150); // Topic
        tblBook.getColumnModel().getColumn(8).setPreferredWidth(120); // Description
        tblBook.getColumnModel().getColumn(9).setPreferredWidth(200); // Notes
        tblBook.getColumnModel().getColumn(10).setPreferredWidth(62); // Quantity
        
        txtLocation.setVisible(false);
        lblLocation.setVisible(false);
        txtStatus.setVisible(false);
        lblStatus.setVisible(false);

        txtQuantity.setVisible(true);
        lblQuantity.setVisible(true);

        lblBookCode.setText("ID");

        // set scroll bar to the beginning
        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);
        
        customizeComponents();
    }

    private void loadTableBookCopy() {
        List<BookCopy> bookCopies = new BookCopyDAO().getAllBookCopies();
    
        String[][] data = new String[bookCopies.size()][13]; 
    
        for (int i = 0; i < bookCopies.size(); i++) {
            data[i][0] = String.valueOf(i + 1); 
            data[i][1] = bookCopies.get(i).getBookID();
            data[i][2] = bookCopies.get(i).getCode();
            data[i][3] = bookCopies.get(i).getLocation();
            data[i][4] = bookCopies.get(i).getStatus();
            data[i][5] = bookCopies.get(i).getBookTitle();
            data[i][6] = bookCopies.get(i).getBookCategory();
            data[i][7] = bookCopies.get(i).getAuthors().toString().replace("[", "").replace("]", "");
            data[i][8] = String.valueOf(bookCopies.get(i).getYearPublished());
            data[i][9] = bookCopies.get(i).getBookPublisher();
            data[i][10] = bookCopies.get(i).getBookTopic();
            data[i][11] = bookCopies.get(i).getBookDescription();
            data[i][12] = bookCopies.get(i).getBookNotes();
        }

        tblBook.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {
                "No.", "ID", "Code", "Location", "Status", "Title", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes"
            }
        ));

        // Đặt kích thước chiều rộng riêng cho từng cột
        tblBook.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblBook.getColumnModel().getColumn(1).setPreferredWidth(80); // ID
        tblBook.getColumnModel().getColumn(2).setPreferredWidth(105); // Code
        tblBook.getColumnModel().getColumn(3).setPreferredWidth(230); // Location
        tblBook.getColumnModel().getColumn(4).setPreferredWidth(80); // Status
        tblBook.getColumnModel().getColumn(5).setPreferredWidth(200); // Title
        tblBook.getColumnModel().getColumn(6).setPreferredWidth(90); // Category
        tblBook.getColumnModel().getColumn(7).setPreferredWidth(200); // Authors
        tblBook.getColumnModel().getColumn(8).setPreferredWidth(100); // Year Published
        tblBook.getColumnModel().getColumn(9).setPreferredWidth(150); // Publisher
        tblBook.getColumnModel().getColumn(10).setPreferredWidth(150); // Topic
        tblBook.getColumnModel().getColumn(11).setPreferredWidth(120); // Description
        tblBook.getColumnModel().getColumn(12).setPreferredWidth(200); // Notes

        txtLocation.setVisible(true);
        lblLocation.setVisible(true);
        txtStatus.setVisible(true);
        lblStatus.setVisible(true);

        txtQuantity.setVisible(false);
        lblQuantity.setVisible(false);

        lblBookCode.setText("Book Code");

        // set vertical scroll bar to the beginning
        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);
        
        customizeComponents();
    }

    private void customizeComponents() {
        // Thay đổi màu của các thanh cuộn


        // Thay đổi nền của tiêu đề bảng
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(240,222,54));
        for (int i = 0; i < tblBook.getColumnModel().getColumnCount(); i++) {
            tblBook.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
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
        lblBookCode = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextField();
        lblLocation = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbManageChoice = new javax.swing.JComboBox<>();
        lblAction = new javax.swing.JLabel();
        cbAction = new javax.swing.JComboBox<>();
        btnConfirm = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAddBookCopy = new javax.swing.JButton();
        DataPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbSearchChoice1 = new javax.swing.JComboBox<>();
        TableScrollPanel = new javax.swing.JScrollPane();
        tblBook = new javax.swing.JTable();
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

        lblBookCode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBookCode.setForeground(new java.awt.Color(215, 19, 19));
        lblBookCode.setText("ID");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(215, 19, 19));
        jLabel10.setText("Category");

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

        lblQuantity.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQuantity.setForeground(new java.awt.Color(215, 19, 19));
        lblQuantity.setText("Quantity");

        txtQuantity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuantity.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtQuantity.setEnabled(false);

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

        lblStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(215, 19, 19));
        lblStatus.setText("Status");

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStatus.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtStatus.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(215, 19, 19));
        jLabel9.setText("Manage");

        cbManageChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book", "Book Copy" }));
        cbManageChoice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbManageChoiceItemStateChanged(evt);
            }
        });

        lblAction.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAction.setForeground(new java.awt.Color(215, 19, 19));
        lblAction.setText("Action");

        cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View All", "View", "Edit", "Add" }));
        cbAction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbActionItemStateChanged(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(240, 222, 54));
        btnConfirm.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(215, 19, 19));
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

        btnAddBookCopy.setBackground(new java.awt.Color(0, 153, 0));
        btnAddBookCopy.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddBookCopy.setForeground(new java.awt.Color(255, 255, 255));
        btnAddBookCopy.setText("Add Book Copy");
        btnAddBookCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBookCopyActionPerformed(evt);
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
                        .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotes))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitle))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InforPanel1Layout.createSequentialGroup()
                        .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(InforPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbManageChoice, 0, 149, Short.MAX_VALUE))
                        .addGroup(InforPanel1Layout.createSequentialGroup()
                            .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbAction, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(btnAddBookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        InforPanel1Layout.setVerticalGroup(
            InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InforPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbManageChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddBookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InforPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        DataPanel.setBackground(new java.awt.Color(13, 18, 130));
        DataPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238,237,237)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(215, 19, 19));
        jLabel1.setText("Search By");

        cbSearchChoice1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        tblBook.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBookMousePressed(evt);
            }
        });
        TableScrollPanel.setViewportView(tblBook);

        cbSearchChoice2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        cbSearchChoice3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        cbSearchChoice4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (tblBook.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (cbManageChoice.getSelectedItem().toString()) {
            case "Book":
                // Kiểm tra xem có Book Copy nào của cuốn sách này đang được mượn không
                String bookID = tblBook.getValueAt(tblBook.getSelectedRow(), 1).toString();

                if (new BookDAO().isBookCopyBorrowed(bookID)) {
                    JOptionPane.showMessageDialog(null, bookID + " has a book copy borrowed. Cannot delete this book", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (new BookDAO().deleteBook(bookID)) {
                    JOptionPane.showMessageDialog(null, "Delete book successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableBook();
                    setFieldsDataEmpty();
                } else {
                    JOptionPane.showMessageDialog(null, "Delete book failed", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "Book Copy":
                if (tblBook.getValueAt(tblBook.getSelectedRow(), 4).toString().equals("Borrowed")) {
                    JOptionPane.showMessageDialog(null, "Cannot delete book copy with status is 'Borrowed'", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String bookID2 = tblBook.getValueAt(tblBook.getSelectedRow(), 1).toString();
                String bookCode = tblBook.getValueAt(tblBook.getSelectedRow(), 2).toString();

                // Xóa Book Copy và giảm số lượng sách
                if (new BookCopyDAO().deleteBookCopy(bookCode)) {
                    new BookDAO().decreaseBookQuantity(bookID2);
                    JOptionPane.showMessageDialog(null, "Delete book copy successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableBookCopy();
                    setFieldsDataEmpty();
                } else {
                    JOptionPane.showMessageDialog(null, "Delete book copy failed", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            default:
                break;
        }
    }

    private void btnAddBookCopyActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblBook.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to add book copy", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bookID = tblBook.getValueAt(tblBook.getSelectedRow(), 1).toString();

        if (new BookCopyDAO().addBookCopy(bookID)) {
            JOptionPane.showMessageDialog(null, "Add book copy successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookCopy();
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Add book copy failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editBook() {
        String title = txtTitle.getText();
        String bookID = txtID.getText();
        String category = txtCategory.getText();
        String author = txtAuthor.getText();
        String year = txtYear.getText();
        String publisher = txtPublisher.getText();
        String topic = txtTopic.getText();
        String description = txtDescription.getText();
        String quantity = txtQuantity.getText();
        String notes = txtNotes.getText();

        if (title.equals("") || bookID.equals("") || category.equals("") || 
            author.equals("") || year.equals("") || publisher.equals("") || 
            topic.equals("") || description.equals("") || quantity.equals("") || notes.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chuyển đổi chuỗi author thành danh sách các tác giả
        List<String> authors = Arrays.asList(author.split(","));
        // Chuyển đổi chuỗi year thành số nguyên
        int yearPublished = Integer.parseInt(year);

        Book book = new Book(title, bookID, category, authors, yearPublished, publisher, topic, description, notes, Integer.parseInt(quantity));

        if (new BookDAO().updateBook(book)) {
            JOptionPane.showMessageDialog(null, "Edit book successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBook();
        } else {
            JOptionPane.showMessageDialog(null, "Edit book failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editBookCopy() {
        String bookCode = txtID.getText();
        String location = txtLocation.getText();
        String status = txtStatus.getText();

        if (bookCode.equals("") || location.equals("") || status.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BookCopy bookCopy = new BookCopy(bookCode, location, status);

        if (new BookCopyDAO().updateBookCopy(bookCopy)) {
            JOptionPane.showMessageDialog(null, "Edit book copy successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBookCopy();
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Edit book copy failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addBook() {
        String title = txtTitle.getText();
        String bookID = txtID.getText();
        String category = txtCategory.getText();
        String author = txtAuthor.getText();
        String year = txtYear.getText();
        String publisher = txtPublisher.getText();
        String topic = txtTopic.getText();
        String description = txtDescription.getText();
        String quantity = txtQuantity.getText();
        String notes = txtNotes.getText();

        if (title.equals("") || bookID.equals("") || category.equals("") || 
            author.equals("") || year.equals("") || publisher.equals("") || 
            topic.equals("") || description.equals("") || quantity.equals("") || notes.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chuyển đổi chuỗi author thành danh sách các tác giả
        List<String> authors = Arrays.asList(author.split(","));
        // Chuyển đổi chuỗi year thành số nguyên
        int yearPublished = Integer.parseInt(year);

        Book book = new Book(title, bookID, category, authors, yearPublished, publisher, topic, description, notes, Integer.parseInt(quantity));

        if (new BookDAO().addBook(book)) {
            JOptionPane.showMessageDialog(null, "Add book successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableBook();
            setFieldsDataEmpty();
        } else {
            JOptionPane.showMessageDialog(null, "Add book failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        String action = cbAction.getSelectedItem().toString();
        String manageChoice = cbManageChoice.getSelectedItem().toString();

        switch (action) {
            case "View All":
                JOptionPane.showMessageDialog(null, "Nothing to confirm here :))", "Message", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "View":
                JOptionPane.showMessageDialog(null, "Nothing to confirm here :))", "Message", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Edit":
                if (manageChoice.equals("Book")) {
                    editBook();
                    cbAction.setSelectedIndex(1);
                } else {
                    editBookCopy();
                    cbAction.setSelectedIndex(1);
                }
                break;
            case "Add":
                if (manageChoice.equals("Book")) {
                    addBook();
                    cbAction.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Book Copy does not support Add function", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                break;
        }
    }

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {
        setFieldsSearchDefault();      
    }

    private void btnSearchAction() {
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
        if ((searchChoice1.equals("Any Field") && searchChoice2.equals("Any Field") && searchChoice3.equals("Any Field") && searchChoice4.equals("Any Field")) || 
            (search1.equals("") && search2.equals("") && search3.equals("") && search4.equals(""))) {
            JOptionPane.showMessageDialog(null, "Please fill in at least 1 search field", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cbManageChoice.getSelectedItem().toString().equals("Book")) {
            loadTableBookSearch(search1, search2, search3, search4, searchChoice1, searchChoice2, searchChoice3, searchChoice4, searchBoolean1, searchBoolean2, searchBoolean3);
        } else {
            loadTableBookCopySearch(search1, search2, search3, search4, searchChoice1, searchChoice2, searchChoice3, searchChoice4, searchBoolean1, searchBoolean2, searchBoolean3);
        }

        cbAction.setSelectedIndex(1);
        setFieldsDataEmpty();
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        btnSearchAction();
    }

    private void loadTableBookSearch(String search1, String search2, String search3, String search4, 
                                     String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                     String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"No.", "ID", "Title", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes", "Quantity"});

        ArrayList<Book> list = new BookDAO().searchBook(search1, search2, search3, search4, 
                            searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                            searchBoolean1, searchBoolean2, searchBoolean3);

        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            model.addRow(new Object[]{i + 1, 
            book.getBookID(),
            book.getBookTitle(), 
            book.getBookCategory(), 
            book.getAuthors().toString().replace("[", "").replace("]", ""), 
            book.getYearPublished(), 
            book.getBookPublisher(), 
            book.getBookTopic(), 
            book.getBookDescription(), 
            book.getBookNotes(), 
            book.getBookQuantity()});
        }

        tblBook.setModel(model);

        tblBook.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblBook.getColumnModel().getColumn(1).setPreferredWidth(80); // ID
        tblBook.getColumnModel().getColumn(2).setPreferredWidth(200); // Title
        tblBook.getColumnModel().getColumn(3).setPreferredWidth(90); // Category
        tblBook.getColumnModel().getColumn(4).setPreferredWidth(200); // Authors
        tblBook.getColumnModel().getColumn(5).setPreferredWidth(100); // Year Published
        tblBook.getColumnModel().getColumn(6).setPreferredWidth(150); // Publisher
        tblBook.getColumnModel().getColumn(7).setPreferredWidth(150); // Topic
        tblBook.getColumnModel().getColumn(8).setPreferredWidth(120); // Description
        tblBook.getColumnModel().getColumn(9).setPreferredWidth(200); // Notes
        tblBook.getColumnModel().getColumn(10).setPreferredWidth(62); // Quantity

        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);


        customizeComponents();
    }

    private void loadTableBookCopySearch(String search1, String search2, String search3, String search4, 
                                         String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                         String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"No.", "ID", "Title", "Code", "Location", "Status", "Category", "Authors", "Year Published", "Publisher", "Topic", "Description", "Notes", "Quantity"});

        ArrayList<BookCopy> list = new BookCopyDAO().searchBookCopy(search1, search2, search3, search4, 
                            searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                            searchBoolean1, searchBoolean2, searchBoolean3);

        for (int i = 0; i < list.size(); i++) {
            BookCopy bookCopy = list.get(i);
            model.addRow(new Object[]{i + 1, 
            bookCopy.getBookID(),
            bookCopy.getCode(), 
            bookCopy.getLocation(), 
            bookCopy.getStatus(), 
            bookCopy.getBookTitle(), 
            bookCopy.getBookCategory(), 
            bookCopy.getAuthors().toString().replace("[", "").replace("]", ""), 
            bookCopy.getYearPublished(), 
            bookCopy.getBookPublisher(), 
            bookCopy.getBookTopic(), 
            bookCopy.getBookDescription(), 
            bookCopy.getBookNotes()});
        }

        tblBook.setModel(model);

        tblBook.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblBook.getColumnModel().getColumn(1).setPreferredWidth(80); // ID
        tblBook.getColumnModel().getColumn(2).setPreferredWidth(105); // Code
        tblBook.getColumnModel().getColumn(3).setPreferredWidth(230); // Location
        tblBook.getColumnModel().getColumn(4).setPreferredWidth(80); // Status
        tblBook.getColumnModel().getColumn(5).setPreferredWidth(200); // Title
        tblBook.getColumnModel().getColumn(6).setPreferredWidth(90); // Category
        tblBook.getColumnModel().getColumn(7).setPreferredWidth(200); // Authors
        tblBook.getColumnModel().getColumn(8).setPreferredWidth(100); // Year Published
        tblBook.getColumnModel().getColumn(9).setPreferredWidth(150); // Publisher
        tblBook.getColumnModel().getColumn(10).setPreferredWidth(150); // Topic
        tblBook.getColumnModel().getColumn(11).setPreferredWidth(120); // Description
        tblBook.getColumnModel().getColumn(12).setPreferredWidth(200); // Notes

        TableScrollPanel.getVerticalScrollBar().setValue(0);
        TableScrollPanel.getHorizontalScrollBar().setValue(0);

        
        customizeComponents();
    }

    private void cbActionItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            switch (cbAction.getSelectedItem().toString()) {
                case "View All":
                    if (cbManageChoice.getSelectedItem().toString().equals("Book")) {
                        loadTableBook();
                    } else {
                        loadTableBookCopy();
                    }
                    setFieldsDataEmpty();
                    setFieldsDataEditable(false);
                    // setFieldsSearchDefault();
                    break;
                case "View":
                    if (tblBook.getSelectedRow() != -1) {
                        setTextBasedOnBookChosen();
                    }
                    setFieldsDataEditable(false);
                    break;
                case "Edit":
                    if (tblBook.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a row to edit", "Error", JOptionPane.ERROR_MESSAGE);
                        cbAction.setSelectedItem("View");
                        setFieldsDataEmpty();
                    } else if (cbManageChoice.getSelectedItem().toString().equals("Book")) {
                        setFieldsDataEditable(true);
                        txtQuantity.setEnabled(false);
                    } else if (cbManageChoice.getSelectedItem().toString().equals("Book Copy")) {
                        txtLocation.setEnabled(true);
                        txtStatus.setEnabled(true);
                    }
                    break;
                case "Add":
                    tblBook.clearSelection();
                    setFieldsDataEditable(true);
                    setFieldsDataEmpty();
                    break;
                default:
                    break;
            }
        }
    }

    private void setTextBasedOnBookChosen(){
        int row = tblBook.getSelectedRow();
        String BookID = tblBook.getValueAt(row, 1).toString();

        Book book = new BookDAO().getBookByID(BookID);

        txtTitle.setText(book.getBookTitle());
        txtID.setText(book.getBookID());
        txtCategory.setText(book.getBookCategory());
        txtAuthor.setText(book.getAuthors().toString().replace("[", "").replace("]", ""));
        txtYear.setText(String.valueOf(book.getYearPublished()));
        txtPublisher.setText(book.getBookPublisher());
        txtTopic.setText(book.getBookTopic());
        txtDescription.setText(book.getBookDescription());
        txtNotes.setText(book.getBookNotes());
        txtQuantity.setText(String.valueOf(book.getBookQuantity()));

        txtTitle.setCaretPosition(0);
        txtID.setCaretPosition(0);
        txtCategory.setCaretPosition(0);
        txtAuthor.setCaretPosition(0);
        txtYear.setCaretPosition(0);
        txtPublisher.setCaretPosition(0);
        txtTopic.setCaretPosition(0);
        txtDescription.setCaretPosition(0);
        txtNotes.setCaretPosition(0);
        txtQuantity.setCaretPosition(0);

        if (cbManageChoice.getSelectedItem().toString().equals("Book Copy")) {
            String BookCode = tblBook.getValueAt(row, 2).toString();
        
            BookCopy bookCopy = new BookCopyDAO().getBookCopyByCode(BookCode);

            txtLocation.setText(bookCopy.getLocation());
            txtStatus.setText(bookCopy.getStatus());
            
            txtID.setText(BookCode);

            txtLocation.setCaretPosition(0);
            txtStatus.setCaretPosition(0);
        }
    }

    private void tblBookMousePressed(java.awt.event.MouseEvent evt) {
        // make user cannot edit any field when double click on a row
        boolean editing = tblBook.isEditing();
        if (editing) {
            tblBook.getCellEditor().stopCellEditing();
        }

        // set cbAction to "View" when user click on a row
        cbAction.setSelectedItem("View");

        // set text fields based on the row user clicked
        setTextBasedOnBookChosen();
    }

    private void cbManageChoiceItemStateChanged(java.awt.event.ItemEvent evt) {
        if (cbManageChoice.getSelectedItem().toString().equals("Book")) {
            loadTableBook();
            cbAction.setSelectedItem("View All");
            cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"View All", "View", "Edit", "Add"}));
            setFieldsSearchDefault();

            btnAddBookCopy.setVisible(false);
            btnAddBookCopy.setEnabled(false);
        } else {
            loadTableBookCopy();
            cbAction.setSelectedItem("View All");
            cbAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"View All", "View", "Edit"}));
            setFieldsSearchDefault();
            btnAddBookCopy.setVisible(true);
            btnAddBookCopy.setEnabled(true);
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
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageBook(new Home()).setVisible(true);
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
    private javax.swing.JButton btnAddBookCopy;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbAction;
    private javax.swing.JComboBox<String> cbManageChoice;
    private javax.swing.JComboBox<String> cbSearchBoolean1;
    private javax.swing.JComboBox<String> cbSearchBoolean2;
    private javax.swing.JComboBox<String> cbSearchBoolean3;
    private javax.swing.JComboBox<String> cbSearchChoice1;
    private javax.swing.JComboBox<String> cbSearchChoice2;
    private javax.swing.JComboBox<String> cbSearchChoice3;
    private javax.swing.JComboBox<String> cbSearchChoice4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAction;
    private javax.swing.JLabel lblBookCode;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblBook;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtNotes;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTopic;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
