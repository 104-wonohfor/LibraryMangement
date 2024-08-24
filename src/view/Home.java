/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.BookCopyDAO;
import DAO.BookDAO;
import DAO.BookRequestDAO;
import DAO.BookshelfDAO;
import DAO.NumberReadRequestDAO;
import DAO.ReaderCardDAO;
import DAO.ReaderDAO;
import DAO.RequestDAO;
import model.Book;
import model.BookCopy;
import model.Reader;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Channel;


import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JTextFieldDateEditor;

import java.time.LocalDate;
import javax.swing.SwingUtilities;

/**
 *
 * @author Thang
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private String personIdNumber = "0";
    private boolean isLibrarian = false;
    
    private static final String EXCHANGE_NAME = "notifications";
    private Channel channel;

    private int numReadRequest = -1;
    private int maxRequest = -1;
    
    public Home() throws Exception {
        setTitle("Ta Quang Buu Library");
        getContentPane().setBackground(java.awt.Color.WHITE);
        initComponents();
        AdminSidePanel.setVisible(false);

        MakeAReaderCardPanel.setVisible(false);
        AdvancedSearchPanel.setVisible(false);
        BookCopyPanel.setVisible(false);
        lblNumberNotification.setVisible(false);
        iconRedNotification.setVisible(false);
        
        AdvSTablePanel.setVisible(false);
        TableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        TableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tblBook.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        JTextFieldDateEditor editorDOB = (JTextFieldDateEditor) dcDOB.getDateEditor();
        editorDOB.setEditable(false);
        
        checkNotify();
        
        setupRabbitMQ();
    }
    
    public void checkNotify(){
        if (isLibrarian){
            numReadRequest = new NumberReadRequestDAO().getNumberReadRequest(personIdNumber);
            System.out.println("numReadRequest: " + numReadRequest);
            maxRequest = new RequestDAO().getLastRequestID();
            System.out.println("maxRequest: " + maxRequest);

            if (maxRequest > numReadRequest) {
                int diffRequest = maxRequest - numReadRequest;

                if (diffRequest < 10) {
                    lblNumberNotification.setLocation(491, 3);
                } else {
                    lblNumberNotification.setLocation(488, 3);
                }

                lblNumberNotification.setText(String.valueOf(diffRequest));

                lblNumberNotification.setVisible(true);
                iconRedNotification.setVisible(true);
            } else {
                lblNumberNotification.setVisible(false);
                iconRedNotification.setVisible(false);
            }
        }        
    }
    
    private void setupRabbitMQ() throws Exception {
        if (true) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "sendBookRequest");
            channel.queueBind(queueName, EXCHANGE_NAME, "cancelBookRequest");
            channel.queueBind(queueName, EXCHANGE_NAME, "extendReaderCard");
            channel.queueBind(queueName, EXCHANGE_NAME, "makeAReaderCardRequest");
    
   
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
        
                // Kiểm tra nếu thông báo là về việc đặt phòng hoặc gọi dịch vụ
                if (message.contains("sendBookRequest") || message.contains("cancelBookRequest") || 
                    message.contains("extendReaderCard") || message.contains("makeAReaderCardRequest")) {
                    SwingUtilities.invokeLater(() -> {
                        setNotiForLibrarian();
                    });
                }
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }
    }

    private void setNotiForLibrarian() {
        System.out.println("isLibrarian: " + isLibrarian);

        if (isLibrarian) {
            int numReadRequest = new NumberReadRequestDAO().getNumberReadRequest(personIdNumber);
            int maxRequest = new RequestDAO().getLastRequestID();
    
            int diffRequest = maxRequest - numReadRequest;
            lblNumberNotification.setVisible(true);
            iconRedNotification.setVisible(true);
            lblNumberNotification.setText(String.valueOf(diffRequest));
        }
    }

    public void showSidePanel() {
        AdminSidePanel.setVisible(true);
    }

    public void setLoginButtonText(String text) {
        btnLogin.setText("Logout");
        lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout_rs.png")));
        btnGreeting.setEnabled(true);
        btnGreeting.setText("Hello, " + text);
    }

    public String getPersonIdNumber() {
        return personIdNumber;
    }

    public void setPersonIdNumber(String idNumber) {
        this.personIdNumber = idNumber;
    }

    public void setIsLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
    }

    public void loadHowToMakeAReaderCard() {
        String text = "Để đáp ứng nhu cầu đọc tài liệu và tìm kiếm thông tin của Bạn đọc ngoài, Trung tâm Truyền thông và Tri thức số - Đại học\n"
                + "Bách Khoa Hà Nội nhận đăng ký làm <span style='color: rgb(175, 26, 42); font-weight: bold;'>THẺ BẠN ĐỌC</span> với các thời hạn và lệ phí (gồm phí thẻ và phí dịch vụ) như sau:\n"       
                + "- 1 tháng = 25.000đ\n"
                + "- 3 tháng = 45.000đ\n"
                + "- 6 tháng = 65.000đ\n"
                + "- 12 tháng (1 năm) = 100.000đ\n"
                + "<b><u>Đối tượng:</u></b>\n"
                + "- Cựu sinh viên ĐHBKHN, Cán bộ hưu trí của ĐHBKHN\n"
                + "- Bạn đọc ngoài trường có nhu cầu sử dụng các dịch vụ của Trung tâm Truyền thông và Tri thức số\n"
                + "<b><u>Địa điểm đăng ký:</u></b>\n"
                + "Phòng 102 - Tòa nhà Tạ Quang Bửu - Đại học Bách khoa Hà Nội\n"
                + "<b><u>Thời gian:</u></b> từ Thứ 2 đến Thứ 6:\n"
                + "Sáng:  từ 08h00 đến 12h00\n"
                + "Chiều: từ 13h30 đến 17h30\n"
                + "<b><u>Thủ tục:</u></b>\n"
                + "- Chứng minh thư nhân dân\n"
                + "- 01 ảnh 3x4\n"
                + "- Điền đầy đủ thông tin vào \"Tờ khai đăng ký làm thẻ bạn đọc\"\n"
                + "Thông tin hỗ trợ tư vấn:\n"
                + "Phòng 404 - Tòa nhà Tạ Quang Bửu, ĐHBKHN\n"
                + "Điện thoại: (024) 2215 3287\n"
                + "Email: tvtqb@hust.edu.vn\n"
                + "<span style='color: rgb(175, 26, 42);'>Bạn đọc đăng ký làm thẻ trực tuyến tại đây:</span>";


        String[] lines = text.split("\\n");
        StringBuilder htmlText = new StringBuilder("<html>");
        for (String line : lines) {
            htmlText.append("<p style='margin-top: 10px;'>").append(line).append("</p>");
        }
        htmlText.append("</html>");
            
        lblMARC.setText(htmlText.toString());
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
        TopPanel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        iconBookShelf = new javax.swing.JLabel();
        btnYourBookshelf = new javax.swing.JLabel();
        lblNotification = new javax.swing.JLabel();
        lblNumberNotification = new javax.swing.JLabel();
        iconRedNotification = new javax.swing.JLabel();
        iconNotification = new javax.swing.JLabel();
        btnGreeting = new javax.swing.JLabel();
        btnYourRequest = new javax.swing.JLabel();
        iconYourRequest = new javax.swing.JLabel();
        MenuPanel = new javax.swing.JPanel();
        btnManageReaderCard = new javax.swing.JLabel();
        btnAdvancedSearch = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        lblMenuBG = new javax.swing.JLabel();
        SearchPanel = new javax.swing.JPanel();
        cbSearchBook = new javax.swing.JComboBox<>();
        txtSearchBook = new javax.swing.JTextField();
        btnHomeSearch = new javax.swing.JButton();
        SideLayeredPane = new javax.swing.JLayeredPane();
        AdminSidePanel = new javax.swing.JPanel();
        Anh1Panel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Anh2Panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Anh3Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelManageBook = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblManageBook = new javax.swing.JLabel();
        PanelManageLibrarian = new javax.swing.JPanel();
        lblManageLibrarian = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PanelManageReader = new javax.swing.JPanel();
        lblManageReader = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PanelManageReaderCard = new javax.swing.JPanel();
        lblManageReaderCard = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PanelBookRequest = new javax.swing.JPanel();
        lblBookRequest = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        UserSidePanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        ContentLayeredPane = new javax.swing.JLayeredPane();
        HomePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        MakeAReaderCardPanel = new javax.swing.JPanel();
        MARCScrollPane = new javax.swing.JScrollPane();
        MARCAllPanel = new javax.swing.JPanel();
        MARCPanel = new javax.swing.JPanel();
        lblMARC = new javax.swing.JLabel();
        MARCRPanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDepartment = new javax.swing.JLabel();
        txtIDNumber = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtPhoneNum = new javax.swing.JTextField();
        cbDuration = new javax.swing.JComboBox<>();
        btnSendMakeAReaderCardRequest = new javax.swing.JButton();
        dcDOB = new com.toedter.calendar.JDateChooser();
        AdvancedSearchPanel = new javax.swing.JPanel();
        AdvancedSearchScrollPane = new javax.swing.JScrollPane();
        AvdSAllPanel = new javax.swing.JPanel();
        AdvSearchPanel = new javax.swing.JPanel();
        txtSearch3 = new javax.swing.JTextField();
        txtSearch4 = new javax.swing.JTextField();
        cbSearchBoolean1 = new javax.swing.JComboBox<>();
        cbSearchChoice1 = new javax.swing.JComboBox<>();
        cbSearchBoolean2 = new javax.swing.JComboBox<>();
        cbSearchBoolean3 = new javax.swing.JComboBox<>();
        cbSearchChoice2 = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        cbSearchChoice3 = new javax.swing.JComboBox<>();
        btnClearSearch = new javax.swing.JButton();
        cbSearchChoice4 = new javax.swing.JComboBox<>();
        txtSearch1 = new javax.swing.JTextField();
        txtSearch2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        AdvSTablePanel = new javax.swing.JPanel();
        TableScrollPane = new javax.swing.JScrollPane();
        tblBook = new javax.swing.JTable();
        BookDataPanel = new javax.swing.JPanel();
        lblBookCode = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtPublisher = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTopic = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextField();
        BookCopyPanel = new javax.swing.JPanel();
        BookCopyScrollPane = new javax.swing.JScrollPane();
        tblBookCopy = new javax.swing.JTable();
        btnSendBookRequest = new javax.swing.JButton();
        btnAddToYourBookshelf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/1200px-Logo_Hust.png")));
        setResizable(false);
        setSize(new java.awt.Dimension(1033, 871));

        BackgroundPanel.setBackground(new java.awt.Color(255, 255, 255));

        TopPanel.setBackground(new java.awt.Color(255, 255, 51));
        TopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 102, 255));
        btnLogin.setText("Login");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
        });
        TopPanel.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, -1, -1));

        lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_rs.png"))); // NOI18N
        TopPanel.add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 35, 35));

        iconBookShelf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        iconBookShelf.setForeground(new java.awt.Color(0, 102, 255));
        iconBookShelf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bookshelf_rs.png"))); // NOI18N
        iconBookShelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconBookShelfMouseEntered(evt);
            }
        });
        TopPanel.add(iconBookShelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 40, 35));

        btnYourBookshelf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnYourBookshelf.setForeground(new java.awt.Color(0, 102, 255));
        btnYourBookshelf.setText("Your Bookshelf");
        btnYourBookshelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnYourBookshelfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnYourBookshelfMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnYourBookshelfMousePressed(evt);
            }
        });
        TopPanel.add(btnYourBookshelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, -1));

        lblNotification.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNotification.setForeground(new java.awt.Color(0, 102, 255));
        lblNotification.setText("Notification");
        lblNotification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNotificationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNotificationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblNotificationMousePressed(evt);
            }
        });
        TopPanel.add(lblNotification, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        lblNumberNotification.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumberNotification.setForeground(new java.awt.Color(255, 255, 255));
        lblNumberNotification.setText("4");
        TopPanel.add(lblNumberNotification, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 3, -1, -1));

        iconRedNotification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ellipse_30.png"))); // NOI18N
        TopPanel.add(iconRedNotification, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 30, 20));

        iconNotification.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        iconNotification.setForeground(new java.awt.Color(0, 102, 255));
        iconNotification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notification_rs.png"))); // NOI18N
        iconNotification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconNotificationMouseEntered(evt);
            }
        });
        TopPanel.add(iconNotification, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 40, 35));

        btnGreeting.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGreeting.setForeground(new java.awt.Color(0, 102, 255));
        btnGreeting.setText(" ");
        btnGreeting.setEnabled(false);
        btnGreeting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGreetingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGreetingMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGreetingMousePressed(evt);
            }
        });
        TopPanel.add(btnGreeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, -1));

        btnYourRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnYourRequest.setForeground(new java.awt.Color(0, 102, 255));
        btnYourRequest.setText("Your Request");
        btnYourRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnYourRequestMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnYourRequestMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnYourRequestMousePressed(evt);
            }
        });
        TopPanel.add(btnYourRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        iconYourRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        iconYourRequest.setForeground(new java.awt.Color(0, 102, 255));
        iconYourRequest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trackRequest_rs.png"))); // NOI18N
        iconYourRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconYourRequestMouseEntered(evt);
            }
        });
        TopPanel.add(iconYourRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 40, 35));

        MenuPanel.setBackground(new java.awt.Color(175, 26, 42));
        MenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnManageReaderCard.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnManageReaderCard.setForeground(new java.awt.Color(255, 255, 255));
        btnManageReaderCard.setText("MAKE A READER CARD");
        btnManageReaderCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnManageReaderCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnManageReaderCardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnManageReaderCardMousePressed(evt);
            }
        });
        MenuPanel.add(btnManageReaderCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, -1, -1));

        btnAdvancedSearch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAdvancedSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnAdvancedSearch.setText("ADVANCED SEARCH");
        btnAdvancedSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdvancedSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdvancedSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdvancedSearchMousePressed(evt);
            }
        });
        MenuPanel.add(btnAdvancedSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, -1, -1));

        btnHome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHomeMousePressed(evt);
            }
        });
        MenuPanel.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, -1, -1));

        lblMenuBG.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bannerTVTQB.png"))); // NOI18N
        MenuPanel.add(lblMenuBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, -1));

        SearchPanel.setBackground(new java.awt.Color(78, 78, 78));

        cbSearchBook.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSearchBook.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        txtSearchBook.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSearchBook.setText("Enter the book information you want to search");
        txtSearchBook.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchBookFocusGained(evt);
            }
        });

        btnHomeSearch.setBackground(new java.awt.Color(255, 153, 51));
        btnHomeSearch.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnHomeSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnHomeSearch.setText("Search");
        btnHomeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cbSearchBook, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearchBook)
                .addGap(18, 18, 18)
                .addComponent(btnHomeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHomeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchBook)
                    .addComponent(cbSearchBook))
                .addContainerGap())
        );

        SideLayeredPane.setPreferredSize(new java.awt.Dimension(214, 546));
        SideLayeredPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdminSidePanel.setBackground(new java.awt.Color(175, 26, 42));

        Anh1Panel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TVTQB_rs.jpg"))); // NOI18N

        javax.swing.GroupLayout Anh1PanelLayout = new javax.swing.GroupLayout(Anh1Panel);
        Anh1Panel.setLayout(Anh1PanelLayout);
        Anh1PanelLayout.setHorizontalGroup(
            Anh1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Anh1PanelLayout.setVerticalGroup(
            Anh1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Anh2Panel.setPreferredSize(new java.awt.Dimension(0, 120));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TVTQB4_rs.jpg"))); // NOI18N

        javax.swing.GroupLayout Anh2PanelLayout = new javax.swing.GroupLayout(Anh2Panel);
        Anh2Panel.setLayout(Anh2PanelLayout);
        Anh2PanelLayout.setHorizontalGroup(
            Anh2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Anh2PanelLayout.setVerticalGroup(
            Anh2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Anh3Panel.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TVTQB2_rs.jpg"))); // NOI18N

        javax.swing.GroupLayout Anh3PanelLayout = new javax.swing.GroupLayout(Anh3Panel);
        Anh3Panel.setLayout(Anh3PanelLayout);
        Anh3PanelLayout.setHorizontalGroup(
            Anh3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Anh3PanelLayout.setVerticalGroup(
            Anh3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelManageBook.setBackground(new java.awt.Color(175, 26, 42));
        PanelManageBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelManageBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelManageBookMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelManageBookMousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/online-library.png"))); // NOI18N
        jLabel9.setToolTipText("");

        lblManageBook.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblManageBook.setForeground(new java.awt.Color(255, 255, 255));
        lblManageBook.setText("Manage Book");
        lblManageBook.setPreferredSize(new java.awt.Dimension(156, 14));

        javax.swing.GroupLayout PanelManageBookLayout = new javax.swing.GroupLayout(PanelManageBook);
        PanelManageBook.setLayout(PanelManageBookLayout);
        PanelManageBookLayout.setHorizontalGroup(
            PanelManageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelManageBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblManageBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelManageBookLayout.setVerticalGroup(
            PanelManageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelManageBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelManageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblManageBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelManageLibrarian.setBackground(new java.awt.Color(175, 26, 42));
        PanelManageLibrarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelManageLibrarianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelManageLibrarianMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelManageLibrarianMousePressed(evt);
            }
        });

        lblManageLibrarian.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblManageLibrarian.setForeground(new java.awt.Color(255, 255, 255));
        lblManageLibrarian.setText("Manage Librarian");
        lblManageLibrarian.setPreferredSize(new java.awt.Dimension(156, 14));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher_2231521.png"))); // NOI18N
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout PanelManageLibrarianLayout = new javax.swing.GroupLayout(PanelManageLibrarian);
        PanelManageLibrarian.setLayout(PanelManageLibrarianLayout);
        PanelManageLibrarianLayout.setHorizontalGroup(
            PanelManageLibrarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelManageLibrarianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblManageLibrarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelManageLibrarianLayout.setVerticalGroup(
            PanelManageLibrarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelManageLibrarianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelManageLibrarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblManageLibrarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelManageReader.setBackground(new java.awt.Color(175, 26, 42));
        PanelManageReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelManageReaderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelManageReaderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelManageReaderMousePressed(evt);
            }
        });

        lblManageReader.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblManageReader.setForeground(new java.awt.Color(255, 255, 255));
        lblManageReader.setText("Manage Reader");
        lblManageReader.setPreferredSize(new java.awt.Dimension(156, 14));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/circle_16154775.png"))); // NOI18N
        jLabel11.setToolTipText("");

        javax.swing.GroupLayout PanelManageReaderLayout = new javax.swing.GroupLayout(PanelManageReader);
        PanelManageReader.setLayout(PanelManageReaderLayout);
        PanelManageReaderLayout.setHorizontalGroup(
            PanelManageReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelManageReaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblManageReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelManageReaderLayout.setVerticalGroup(
            PanelManageReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelManageReaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelManageReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblManageReader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelManageReaderCard.setBackground(new java.awt.Color(175, 26, 42));
        PanelManageReaderCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelManageReaderCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelManageReaderCardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelManageReaderCardMousePressed(evt);
            }
        });

        lblManageReaderCard.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblManageReaderCard.setForeground(new java.awt.Color(255, 255, 255));
        lblManageReaderCard.setText("Manage Reader Card");
        lblManageReaderCard.setPreferredSize(new java.awt.Dimension(156, 14));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ReaderCard.png"))); // NOI18N
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout PanelManageReaderCardLayout = new javax.swing.GroupLayout(PanelManageReaderCard);
        PanelManageReaderCard.setLayout(PanelManageReaderCardLayout);
        PanelManageReaderCardLayout.setHorizontalGroup(
            PanelManageReaderCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelManageReaderCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblManageReaderCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelManageReaderCardLayout.setVerticalGroup(
            PanelManageReaderCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelManageReaderCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelManageReaderCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblManageReaderCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelBookRequest.setBackground(new java.awt.Color(175, 26, 42));
        PanelBookRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelBookRequestMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelBookRequestMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelBookRequestMousePressed(evt);
            }
        });

        lblBookRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBookRequest.setForeground(new java.awt.Color(255, 255, 255));
        lblBookRequest.setText("Book Request");
        lblBookRequest.setPreferredSize(new java.awt.Dimension(156, 14));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bookRequest.png"))); // NOI18N
        jLabel13.setToolTipText("");

        javax.swing.GroupLayout PanelBookRequestLayout = new javax.swing.GroupLayout(PanelBookRequest);
        PanelBookRequest.setLayout(PanelBookRequestLayout);
        PanelBookRequestLayout.setHorizontalGroup(
            PanelBookRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBookRequestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelBookRequestLayout.setVerticalGroup(
            PanelBookRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBookRequestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBookRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBookRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout AdminSidePanelLayout = new javax.swing.GroupLayout(AdminSidePanel);
        AdminSidePanel.setLayout(AdminSidePanelLayout);
        AdminSidePanelLayout.setHorizontalGroup(
            AdminSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelManageBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelManageLibrarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelManageReader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelManageReaderCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Anh3Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Anh1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelBookRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Anh2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );
        AdminSidePanelLayout.setVerticalGroup(
            AdminSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSidePanelLayout.createSequentialGroup()
                .addComponent(PanelManageBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(PanelManageLibrarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(PanelManageReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(PanelManageReaderCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Anh3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Anh2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(Anh1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        SideLayeredPane.add(AdminSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        UserSidePanel.setBackground(new java.awt.Color(175, 26, 42));
        UserSidePanel.setPreferredSize(new java.awt.Dimension(214, 666));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vertical_Banner5_rs.png"))); // NOI18N

        javax.swing.GroupLayout UserSidePanelLayout = new javax.swing.GroupLayout(UserSidePanel);
        UserSidePanel.setLayout(UserSidePanelLayout);
        UserSidePanelLayout.setHorizontalGroup(
            UserSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UserSidePanelLayout.setVerticalGroup(
            UserSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        SideLayeredPane.add(UserSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ContentLayeredPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HomePanel.setBackground(new java.awt.Color(255, 255, 255));
        HomePanel.setPreferredSize(new java.awt.Dimension(817, 666));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot_6.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        ContentLayeredPane.add(HomePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        MakeAReaderCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        MakeAReaderCardPanel.setPreferredSize(new java.awt.Dimension(817, 666));
        MakeAReaderCardPanel.setLayout(null);

        MARCScrollPane.setPreferredSize(new java.awt.Dimension(817, 666));

        MARCAllPanel.setBackground(new java.awt.Color(255, 255, 255));
        MARCAllPanel.setPreferredSize(new java.awt.Dimension(795, 930));

        MARCPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblMARC.setBackground(new java.awt.Color(255, 255, 255));
        lblMARC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMARC.setText("jLabel2");
        lblMARC.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout MARCPanelLayout = new javax.swing.GroupLayout(MARCPanel);
        MARCPanel.setLayout(MARCPanelLayout);
        MARCPanelLayout.setHorizontalGroup(
            MARCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MARCPanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(lblMARC, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MARCPanelLayout.setVerticalGroup(
            MARCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMARC, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        MARCRPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Name");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Address");

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAddress.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        lblDOB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDOB.setForeground(new java.awt.Color(51, 51, 51));
        lblDOB.setText("DOB");

        txtGender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGender.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Gender");
        jLabel26.setToolTipText("");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Email");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        lblDepartment.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDepartment.setForeground(new java.awt.Color(51, 51, 51));
        lblDepartment.setText("Duration");

        txtIDNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIDNumber.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("ID Number");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Phone Num");

        txtPhoneNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPhoneNum.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        cbDuration.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Month", "3 Months", "6 Months", "1 Year" }));

        btnSendMakeAReaderCardRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSendMakeAReaderCardRequest.setText("Send Request");
        btnSendMakeAReaderCardRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMakeAReaderCardRequestActionPerformed(evt);
            }
        });

        dcDOB.setDateFormatString("yyyy-MM-dd\n");
        dcDOB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout MARCRPanelLayout = new javax.swing.GroupLayout(MARCRPanel);
        MARCRPanel.setLayout(MARCRPanelLayout);
        MARCRPanelLayout.setHorizontalGroup(
            MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MARCRPanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MARCRPanelLayout.createSequentialGroup()
                        .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MARCRPanelLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MARCRPanelLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbDuration, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MARCRPanelLayout.createSequentialGroup()
                        .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MARCRPanelLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MARCRPanelLayout.createSequentialGroup()
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
                .addGap(88, 88, 88))
            .addGroup(MARCRPanelLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(btnSendMakeAReaderCardRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MARCRPanelLayout.setVerticalGroup(
            MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MARCRPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MARCRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnSendMakeAReaderCardRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MARCAllPanelLayout = new javax.swing.GroupLayout(MARCAllPanel);
        MARCAllPanel.setLayout(MARCAllPanelLayout);
        MARCAllPanelLayout.setHorizontalGroup(
            MARCAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MARCAllPanelLayout.createSequentialGroup()
                .addComponent(MARCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(MARCRPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MARCAllPanelLayout.setVerticalGroup(
            MARCAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MARCAllPanelLayout.createSequentialGroup()
                .addComponent(MARCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MARCRPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        MARCScrollPane.setViewportView(MARCAllPanel);

        MakeAReaderCardPanel.add(MARCScrollPane);
        MARCScrollPane.setBounds(0, 0, 817, 666);

        ContentLayeredPane.add(MakeAReaderCardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        AdvancedSearchPanel.setBackground(new java.awt.Color(255, 255, 255));
        AdvancedSearchPanel.setPreferredSize(new java.awt.Dimension(817, 666));
        AdvancedSearchPanel.setLayout(null);

        AdvancedSearchScrollPane.setPreferredSize(new java.awt.Dimension(817, 666));

        AvdSAllPanel.setBackground(new java.awt.Color(255, 255, 255));
        AvdSAllPanel.setPreferredSize(new java.awt.Dimension(795, 840));

        AdvSearchPanel.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSearch4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbSearchBoolean1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        cbSearchChoice1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        cbSearchBoolean2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        cbSearchBoolean3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchBoolean3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "AND NOT", "OR" }));

        cbSearchChoice2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_rs.png"))); // NOI18N
        btnSearch.setPreferredSize(new java.awt.Dimension(48, 48));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbSearchChoice3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        btnClearSearch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClearSearch.setForeground(new java.awt.Color(0, 153, 0));
        btnClearSearch.setText("Clear");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        cbSearchChoice4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSearchChoice4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Field", "ID", "Title", "Author", "Year Published", "Publisher", "Category" }));

        txtSearch1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSearch2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Search By");

        javax.swing.GroupLayout AdvSearchPanelLayout = new javax.swing.GroupLayout(AdvSearchPanel);
        AdvSearchPanel.setLayout(AdvSearchPanelLayout);
        AdvSearchPanelLayout.setHorizontalGroup(
            AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdvSearchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AdvSearchPanelLayout.createSequentialGroup()
                        .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbSearchChoice3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch1)
                            .addComponent(txtSearch2)
                            .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AdvSearchPanelLayout.createSequentialGroup()
                        .addComponent(cbSearchChoice4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSearchBoolean1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AdvSearchPanelLayout.createSequentialGroup()
                        .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSearchBoolean2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSearchBoolean3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        AdvSearchPanelLayout.setVerticalGroup(
            AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvSearchPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSearchBoolean1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdvSearchPanelLayout.createSequentialGroup()
                        .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbSearchBoolean2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSearchChoice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbSearchBoolean3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdvSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSearchChoice4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        AdvSTablePanel.setBackground(new java.awt.Color(255, 255, 255));

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
        TableScrollPane.setViewportView(tblBook);

        javax.swing.GroupLayout AdvSTablePanelLayout = new javax.swing.GroupLayout(AdvSTablePanel);
        AdvSTablePanel.setLayout(AdvSTablePanelLayout);
        AdvSTablePanelLayout.setHorizontalGroup(
            AdvSTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvSTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TableScrollPane)
                .addContainerGap())
        );
        AdvSTablePanelLayout.setVerticalGroup(
            AdvSTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvSTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BookDataPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblBookCode.setBackground(new java.awt.Color(51, 51, 51));
        lblBookCode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBookCode.setForeground(new java.awt.Color(51, 51, 51));
        lblBookCode.setText("ID");

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Category");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtID.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtCategory.setEditable(false);
        txtCategory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCategory.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel24.setBackground(new java.awt.Color(51, 51, 51));
        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Author(s)");

        txtAuthor.setEditable(false);
        txtAuthor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAuthor.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel30.setBackground(new java.awt.Color(51, 51, 51));
        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Year");

        txtYear.setEditable(false);
        txtYear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtYear.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel31.setBackground(new java.awt.Color(51, 51, 51));
        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Publisher");

        txtPublisher.setEditable(false);
        txtPublisher.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPublisher.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel32.setBackground(new java.awt.Color(51, 51, 51));
        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Topic");

        txtTopic.setEditable(false);
        txtTopic.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTopic.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel33.setBackground(new java.awt.Color(51, 51, 51));
        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Description");

        txtDescription.setEditable(false);
        txtDescription.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescription.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        lblQuantity.setBackground(new java.awt.Color(51, 51, 51));
        lblQuantity.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQuantity.setForeground(new java.awt.Color(51, 51, 51));
        lblQuantity.setText("Quantity");

        txtQuantity.setEditable(false);
        txtQuantity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuantity.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Title");

        txtTitle.setEditable(false);
        txtTitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTitle.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel34.setBackground(new java.awt.Color(51, 51, 51));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Notes");

        txtNotes.setEditable(false);
        txtNotes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNotes.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        tblBookCopy.setModel(new javax.swing.table.DefaultTableModel(
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
        BookCopyScrollPane.setViewportView(tblBookCopy);

        javax.swing.GroupLayout BookCopyPanelLayout = new javax.swing.GroupLayout(BookCopyPanel);
        BookCopyPanel.setLayout(BookCopyPanelLayout);
        BookCopyPanelLayout.setHorizontalGroup(
            BookCopyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BookCopyScrollPane)
        );
        BookCopyPanelLayout.setVerticalGroup(
            BookCopyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BookCopyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        btnSendBookRequest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSendBookRequest.setText("Send Book Request");
        btnSendBookRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendBookRequestActionPerformed(evt);
            }
        });

        btnAddToYourBookshelf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddToYourBookshelf.setText("Add To Your Bookshelf");
        btnAddToYourBookshelf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToYourBookshelfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BookDataPanelLayout = new javax.swing.GroupLayout(BookDataPanel);
        BookDataPanel.setLayout(BookDataPanelLayout);
        BookDataPanelLayout.setHorizontalGroup(
            BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookDataPanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(btnSendBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddToYourBookshelf, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BookCopyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotes))
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitle))
                    .addGroup(BookDataPanelLayout.createSequentialGroup()
                        .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
        );
        BookDataPanelLayout.setVerticalGroup(
            BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookDataPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBookCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BookCopyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BookDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddToYourBookshelf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AvdSAllPanelLayout = new javax.swing.GroupLayout(AvdSAllPanel);
        AvdSAllPanel.setLayout(AvdSAllPanelLayout);
        AvdSAllPanelLayout.setHorizontalGroup(
            AvdSAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdvSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AdvSTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BookDataPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AvdSAllPanelLayout.setVerticalGroup(
            AvdSAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AvdSAllPanelLayout.createSequentialGroup()
                .addComponent(AdvSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdvSTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        AdvancedSearchScrollPane.setViewportView(AvdSAllPanel);

        AdvancedSearchPanel.add(AdvancedSearchScrollPane);
        AdvancedSearchScrollPane.setBounds(0, 0, 817, 666);

        ContentLayeredPane.add(AdvancedSearchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addComponent(SideLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentLayeredPane))
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SideLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ContentLayeredPane)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddToYourBookshelfActionPerformed(java.awt.event.ActionEvent evt) {
        if (isLibrarian) {
            JOptionPane.showMessageDialog(null, "Librarian cannot add book to bookshelf", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (personIdNumber.equals("0")) {
            JOptionPane.showMessageDialog(null, "Please login first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tblBookCopy.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please choose a book to add to your bookshelf", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblBookCopy.getSelectedRow();

        String BookCode = tblBookCopy.getValueAt(row, 1).toString();

        // get ReaderCardID from personIdNumber
        String readerCardID = new ReaderDAO().getReaderCardIDFromPersonIDNumber(personIdNumber);

        // if bookCode is already in bookshelf
        if (new BookshelfDAO().isBookInBookshelf(readerCardID, BookCode)) {
            JOptionPane.showMessageDialog(null, "This book is already in your bookshelf", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // add book to bookshelf
        if (new BookshelfDAO().addBookToBookshelf(readerCardID, BookCode)) {
            JOptionPane.showMessageDialog(null, "Book added to your bookshelf successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Add book to your bookshelf failed", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void btnSendMakeAReaderCardRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (isLibrarian) {
            JOptionPane.showMessageDialog(null, "Librarian cannot send make a reader card request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!personIdNumber.equals("0")) {
            JOptionPane.showMessageDialog(null, "You already have a reader card", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!checkFieldsAcceptable()) {
            return;
        }

        String name = txtName.getText();
        String dob = ((JTextField)dcDOB.getDateEditor().getUiComponent()).getText();
        String address = txtAddress.getText();
        String gender = txtGender.getText();
        String phoneNum = txtPhoneNum.getText();
        String email = txtEmail.getText();
        String idNumber = txtIDNumber.getText();
        String duration = cbDuration.getSelectedItem().toString();

        LocalDate dateIssued = LocalDate.now();
        LocalDate dateExpired = null;

        if (duration.equals("1 Month")) {
            dateExpired = dateIssued.plusMonths(1);
        } else if (duration.equals("3 Months")) {
            dateExpired = dateIssued.plusMonths(3);
        } else if (duration.equals("6 Months")) {
            dateExpired = dateIssued.plusMonths(6);
        } else if (duration.equals("1 Year")) {
            dateExpired = dateIssued.plusYears(1);
        }

        if (name.equals("") || dob.equals("") || address.equals("") || gender.equals("") || 
            phoneNum.equals("") || email.equals("") || idNumber.equals("") || duration.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String readerCardId = new ReaderCardDAO().getLatestReaderCardID();   
        
        if (new ReaderCardDAO().addReaderCard(readerCardId, dateIssued, dateExpired) && new ReaderDAO().addReader(name, dob, address, gender, phoneNum, email, idNumber, readerCardId)) {
            JOptionPane.showMessageDialog(null, "Make a reader card request sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            // clear all text fields
            txtName.setText("");
            dcDOB.setDate(null);
            txtAddress.setText("");
            txtGender.setText("");
            txtPhoneNum.setText("");
            txtEmail.setText("");
            txtIDNumber.setText("");
            cbDuration.setSelectedIndex(0);
            
            new RequestDAO().insertRequest("A new READER CARD REQUEST has been sent");

            try {
                new Reader().makeAReaderCardRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Make a reader card request failed", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    } 

    private void btnSendBookRequestActionPerformed(java.awt.event.ActionEvent evt) {
        if (isLibrarian) {
            JOptionPane.showMessageDialog(null, "Librarian cannot send book request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (personIdNumber.equals("0")) {
            JOptionPane.showMessageDialog(null, "Please login first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (tblBookCopy.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please choose a book to send request", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblBookCopy.getSelectedRow();

        String status = tblBookCopy.getValueAt(row, 2).toString();
        if (!status.equals("Available")) {
            JOptionPane.showMessageDialog(null, "This book is not available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String BookCopyID = tblBookCopy.getValueAt(row, 1).toString();


        // get ReaderCardID from personIdNumber
        String readerCardID = new ReaderDAO().getReaderCardIDFromPersonIDNumber(personIdNumber);

        // send book request
        if (new BookRequestDAO().sendBookRequest(readerCardID, BookCopyID, "Requested")) {
            JOptionPane.showMessageDialog(null, "Book request sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            new RequestDAO().insertRequest(readerCardID + " REQUESTED book(s) ");

            try {
                new Reader().sendBookRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Book request failed", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnHomeSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String search = txtSearchBook.getText();
        String searchChoice = cbSearchBook.getSelectedItem().toString();

        // if cbSearchBook is "Any Field" or txtSearchBook is empty, Announce to user
        if (searchChoice.equals("Any Field")) {
            JOptionPane.showMessageDialog(null, "Please choose a search field", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (search.equals("") || search.equals(" ") || search.equals("Enter the book information you want to search")) {
            JOptionPane.showMessageDialog(null, "Please enter a search keyword", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            HomePanel.setVisible(false);
            MakeAReaderCardPanel.setVisible(false);

            AdvancedSearchPanel.setVisible(true);

            loadTableBookSearch(search, search, search, search, searchChoice, searchChoice, searchChoice, searchChoice, "AND", "AND", "AND");
            AdvSTablePanel.setVisible(true);
            cbSearchChoice1.setSelectedItem(searchChoice);
            txtSearch1.setText(search);
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
        loadTableBookSearch(search1, search2, search3, search4, searchChoice1, searchChoice2, searchChoice3, searchChoice4, searchBoolean1, searchBoolean2, searchBoolean3);
        AdvSTablePanel.setVisible(true);
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
        
        TableScrollPane.getVerticalScrollBar().setValue(0);
        TableScrollPane.getHorizontalScrollBar().setValue(0);

        //customizeComponents();
    }
        
    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {
        setFieldsSearchDefault(); 
    }

    private void loadTableBookCopy(String BookID) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"No.", "Copy ID", "Status", "Location"});

        ArrayList<BookCopy> list = new BookCopyDAO().getBookCopyByBookID(BookID);

        for (int i = 0; i < list.size(); i++) {
            BookCopy bookCopy = list.get(i);
            model.addRow(new Object[]{i + 1, bookCopy.getCode(), bookCopy.getStatus(), bookCopy.getLocation()});
        }

        tblBookCopy.setModel(model);

        tblBookCopy.getColumnModel().getColumn(0).setPreferredWidth(50); // No.
        tblBookCopy.getColumnModel().getColumn(1).setPreferredWidth(80); // Book Code
        tblBookCopy.getColumnModel().getColumn(2).setPreferredWidth(80); // Statusd
        tblBookCopy.getColumnModel().getColumn(3).setPreferredWidth(240); // Location

        BookCopyScrollPane.getVerticalScrollBar().setValue(0);
        BookCopyScrollPane.getHorizontalScrollBar().setValue(0);

        //customizeComponents();
    }

    private void tblBookMousePressed(java.awt.event.MouseEvent evt) {
        boolean editing = tblBook.isEditing();
        if (editing) {
            tblBook.getCellEditor().stopCellEditing();
        }

        // set text fields based on the row user clicked
        setTextBasedOnBookChosen();
        
        // Load Table Book Copy
        int row = tblBook.getSelectedRow();
        String BookID = tblBook.getValueAt(row, 1).toString();

        BookCopyPanel.setVisible(true);
        loadTableBookCopy(BookID);
    }

    private void btnAdvancedSearchMousePressed(java.awt.event.MouseEvent evt) {
        AdvancedSearchPanel.setVisible(true);
        HomePanel.setVisible(false);
        MakeAReaderCardPanel.setVisible(false);
    }

    private void btnAdvancedSearchMouseEntered(java.awt.event.MouseEvent evt) {
        btnAdvancedSearch.setForeground(new java.awt.Color(239, 209, 212));
    }

    private void btnAdvancedSearchMouseExited(java.awt.event.MouseEvent evt) {
        btnAdvancedSearch.setForeground(new java.awt.Color(255, 255, 255));
    }

    private void btnManageReaderCardMousePressed(java.awt.event.MouseEvent evt) {
        MakeAReaderCardPanel.setVisible(true);
        HomePanel.setVisible(false);
        AdvancedSearchPanel.setVisible(false);

        loadHowToMakeAReaderCard();

        // set position of scroll bar to top
        MARCScrollPane.getVerticalScrollBar().setValue(0);
    }

    private void btnManageReaderCardMouseEntered(java.awt.event.MouseEvent evt) {
        btnManageReaderCard.setForeground(new java.awt.Color(239, 209, 212));
    }

    private void btnManageReaderCardMouseExited(java.awt.event.MouseEvent evt) {
        btnManageReaderCard.setForeground(new java.awt.Color(255, 255, 255));
    }

    private void btnHomeMousePressed(java.awt.event.MouseEvent evt) {
        HomePanel.setVisible(true);
        MakeAReaderCardPanel.setVisible(false);
        AdvancedSearchPanel.setVisible(false);
    }

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {
        btnHome.setForeground(new java.awt.Color(239, 209, 212));
    }                                     

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
    }

    private void txtSearchBookFocusGained(java.awt.event.FocusEvent evt) {                                          
        if (txtSearchBook.getText().equals("Enter the book information you want to search")) {
            txtSearchBook.setText("");
        }
    }

    private void btnYourRequestMouseEntered(java.awt.event.MouseEvent evt) {
        String text = btnYourRequest.getText();
        btnYourRequest.setText("<html><u>" + text + "</u></html>");
    }

    private void btnYourRequestMouseExited(java.awt.event.MouseEvent evt) {
        String text = btnYourRequest.getText();
        if (text.contains("<html><u>")) {
            text = text.replace("<html><u>", "");
            text = text.replace("</u></html>", "");
            btnYourRequest.setText(text);
        }

    }

    private void btnYourRequestMousePressed(java.awt.event.MouseEvent evt) {
        if (personIdNumber.equals("0")) {
            JOptionPane.showMessageDialog(null, "Please login first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (isLibrarian) {
            JOptionPane.showMessageDialog(null, "Librarian does not have this feature! :((", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else { 
            String readerCardID = new ReaderDAO().getReaderCardIDFromPersonIDNumber(personIdNumber);

            UserRequest f = new UserRequest(this, readerCardID);
            f.setVisible(true);
            this.setEnabled(false);
        }
    }

    private void btnYourBookshelfMousePressed(java.awt.event.MouseEvent evt) {
        if (personIdNumber.equals("0")) {
            JOptionPane.showMessageDialog(null, "You need to login first!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (isLibrarian) {
            JOptionPane.showMessageDialog(null, "Librarian cannot access this feature! :((", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String readerCardID = new ReaderDAO().getReaderCardIDFromPersonIDNumber(personIdNumber);
            
            YourBookshelf f = new YourBookshelf(this, readerCardID);
            f.setVisible(true);
            this.setEnabled(false);
        }
    }

    private void btnYourBookshelfMouseEntered(java.awt.event.MouseEvent evt) {
        String text = btnYourBookshelf.getText();
        btnYourBookshelf.setText("<html><u>" + text + "</u></html>");
    }

    private void btnYourBookshelfMouseExited(java.awt.event.MouseEvent evt) {
        String text = btnYourBookshelf.getText();
        if (text.contains("<html><u>")) {
            text = text.replace("<html><u>", "");
            text = text.replace("</u></html>", "");
            btnYourBookshelf.setText(text);
        }
    }

    private void btnGreetingMousePressed(java.awt.event.MouseEvent evt) {
        if (personIdNumber.equals("0")) {
            return;
        } else {
            if (isLibrarian) {
                InfoLibrarian f = new InfoLibrarian(this, personIdNumber);
                f.setVisible(true);
                this.setEnabled(false);
            } else {
                InfoReader f = new InfoReader(this, personIdNumber);
                f.setVisible(true);
                this.setEnabled(false);
            }
        }
    }

    private void btnGreetingMouseEntered(java.awt.event.MouseEvent evt) {
        String text = btnGreeting.getText();
        btnGreeting.setText("<html><u>" + text + "</u></html>");
    }

    private void btnGreetingMouseExited(java.awt.event.MouseEvent evt) {
        String text = btnGreeting.getText();
        if (text.contains("<html><u>")) {
            text = text.replace("<html><u>", "");
            text = text.replace("</u></html>", "");
            btnGreeting.setText(text);
        }
    }

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {
        String text = btnLogin.getText();
        btnLogin.setText("<html><u>" + text + "</u></html>");
    }

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {
        String text = btnLogin.getText();
        if (text.contains("<html><u>")) {
            text = text.replace("<html><u>", "");
            text = text.replace("</u></html>", "");
            btnLogin.setText(text);
        }
    }

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {
        String text = btnLogin.getText();
    
        if (text.contains("Login")) {
            Login f = new Login(this);
            f.setVisible(true); 
            this.setEnabled(false);   
        } else if (text.contains("Logout")) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                btnLogin.setText("Login");
                lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_rs.png")));
                btnGreeting.setText(" ");
                btnGreeting.setEnabled(false);
                AdminSidePanel.setVisible(false);
                personIdNumber = "0";
                isLibrarian = false;
            } 
        }
    }
    
    private Notification notificationInstance;
    private void iconBookShelfMouseEntered(java.awt.event.MouseEvent evt) {
        if (Notification.isOpen) {
            notificationInstance.dispose();
        }
    }

    private void iconYourRequestMouseEntered(java.awt.event.MouseEvent evt) {
        if (Notification.isOpen) {
            notificationInstance.dispose();
        }
    }

    private void lblNotificationMouseEntered(java.awt.event.MouseEvent evt) {
        String text = lblNotification.getText();
        lblNotification.setText("<html><u>" + text + "</u></html>");
    }

    private void lblNotificationMouseExited(java.awt.event.MouseEvent evt) {
        lblNotification.setText("Notification");
    }

    private void lblNotificationMousePressed(java.awt.event.MouseEvent evt) {
        if (Notification.isOpen) {
            notificationInstance.dispose();
        }
        
        numReadRequest = new NumberReadRequestDAO().getNumberReadRequest(personIdNumber);
        maxRequest = new RequestDAO().getLastRequestID();

        lblNumberNotification.setVisible(false);
        iconRedNotification.setVisible(false);
        lblNumberNotification.setText("");

        if (personIdNumber.equals("0")){
            notificationInstance = new Notification(this);
            notificationInstance.setVisible(true);   
        } else {
            notificationInstance = new Notification(this);
            notificationInstance.setVisible(true);
            notificationInstance.setPanelNotVisible();   
        }
        
        notificationInstance.loadRequestTable(maxRequest - numReadRequest);


        new NumberReadRequestDAO().updateNumberReadRequest(personIdNumber, maxRequest);
    }

    private void iconNotificationMouseEntered(java.awt.event.MouseEvent evt) {
        if (Notification.isOpen) {
            notificationInstance.dispose();
        }
    }

    private void PanelManageBookMousePressed(java.awt.event.MouseEvent evt) {
        ManageBook f = new ManageBook(this);
        f.setVisible(true);
        this.setEnabled(false);
    }

    private void PanelManageBookMouseEntered(java.awt.event.MouseEvent evt) {
        PanelManageBook.setBackground(java.awt.Color.WHITE);
        lblManageBook.setForeground(java.awt.Color.BLACK);
    }

    private void PanelManageBookMouseExited(java.awt.event.MouseEvent evt) {
        PanelManageBook.setBackground(new java.awt.Color(175, 26, 42));
        lblManageBook.setForeground(java.awt.Color.WHITE);
    }

    private void PanelManageLibrarianMousePressed(java.awt.event.MouseEvent evt) {
        if (!personIdNumber.equals("040203001921")) {
            JOptionPane.showMessageDialog(null, "You do not have permission to access this feature", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ManageLibrarian f = new ManageLibrarian(this);
        f.setVisible(true);
        this.setEnabled(false);
    }

    private void PanelManageLibrarianMouseEntered(java.awt.event.MouseEvent evt) {
        PanelManageLibrarian.setBackground(java.awt.Color.WHITE);
        lblManageLibrarian.setForeground(java.awt.Color.BLACK);
    }

    private void PanelManageLibrarianMouseExited(java.awt.event.MouseEvent evt) {
        PanelManageLibrarian.setBackground(new java.awt.Color(175, 26, 42));
        lblManageLibrarian.setForeground(java.awt.Color.WHITE);
    }

    private void PanelManageReaderMousePressed(java.awt.event.MouseEvent evt) {
        ManageReader f = new ManageReader(this);
        f.setVisible(true);
        this.setEnabled(false);
    }

    private void PanelManageReaderMouseEntered(java.awt.event.MouseEvent evt) {
        PanelManageReader.setBackground(java.awt.Color.WHITE);
        lblManageReader.setForeground(java.awt.Color.BLACK);
    }

    private void PanelManageReaderMouseExited(java.awt.event.MouseEvent evt) {
        PanelManageReader.setBackground(new java.awt.Color(175, 26, 42));
        lblManageReader.setForeground(java.awt.Color.WHITE);
    }

    private void PanelManageReaderCardMousePressed(java.awt.event.MouseEvent evt) {
        ManageReaderCard f = new ManageReaderCard(this);
        f.setVisible(true);
        this.setEnabled(false);
    }

    private void PanelManageReaderCardMouseEntered(java.awt.event.MouseEvent evt) {
        PanelManageReaderCard.setBackground(java.awt.Color.WHITE);
        lblManageReaderCard.setForeground(java.awt.Color.BLACK);
    }

    private void PanelManageReaderCardMouseExited(java.awt.event.MouseEvent evt) {
        PanelManageReaderCard.setBackground(new java.awt.Color(175, 26, 42));
        lblManageReaderCard.setForeground(java.awt.Color.WHITE);
    }

    private void PanelBookRequestMousePressed(java.awt.event.MouseEvent evt) {
        ManageBookRequest f = new ManageBookRequest(this);
        f.setVisible(true);
        this.setEnabled(false);
    }

    private void PanelBookRequestMouseEntered(java.awt.event.MouseEvent evt) {
        PanelBookRequest.setBackground(java.awt.Color.WHITE);
        lblBookRequest.setForeground(java.awt.Color.BLACK);
    }

    private void PanelBookRequestMouseExited(java.awt.event.MouseEvent evt) {
        PanelBookRequest.setBackground(new java.awt.Color(175, 26, 42));
        lblBookRequest.setForeground(java.awt.Color.WHITE);
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Home().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminSidePanel;
    private javax.swing.JPanel AdvSTablePanel;
    private javax.swing.JPanel AdvSearchPanel;
    private javax.swing.JPanel AdvancedSearchPanel;
    private javax.swing.JScrollPane AdvancedSearchScrollPane;
    private javax.swing.JPanel Anh1Panel;
    private javax.swing.JPanel Anh2Panel;
    private javax.swing.JPanel Anh3Panel;
    private javax.swing.JPanel AvdSAllPanel;
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel BookCopyPanel;
    private javax.swing.JScrollPane BookCopyScrollPane;
    private javax.swing.JPanel BookDataPanel;
    private javax.swing.JLayeredPane ContentLayeredPane;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JPanel MARCAllPanel;
    private javax.swing.JPanel MARCPanel;
    private javax.swing.JPanel MARCRPanel;
    private javax.swing.JScrollPane MARCScrollPane;
    private javax.swing.JPanel MakeAReaderCardPanel;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PanelBookRequest;
    private javax.swing.JPanel PanelManageBook;
    private javax.swing.JPanel PanelManageLibrarian;
    private javax.swing.JPanel PanelManageReader;
    private javax.swing.JPanel PanelManageReaderCard;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLayeredPane SideLayeredPane;
    private javax.swing.JScrollPane TableScrollPane;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JPanel UserSidePanel;
    private javax.swing.JButton btnAddToYourBookshelf;
    private javax.swing.JLabel btnAdvancedSearch;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JLabel btnGreeting;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnHomeSearch;
    private javax.swing.JLabel btnLogin;
    private javax.swing.JLabel btnManageReaderCard;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSendBookRequest;
    private javax.swing.JButton btnSendMakeAReaderCardRequest;
    private javax.swing.JLabel btnYourBookshelf;
    private javax.swing.JLabel btnYourRequest;
    private javax.swing.JComboBox<String> cbDuration;
    private javax.swing.JComboBox<String> cbSearchBook;
    private javax.swing.JComboBox<String> cbSearchBoolean1;
    private javax.swing.JComboBox<String> cbSearchBoolean2;
    private javax.swing.JComboBox<String> cbSearchBoolean3;
    private javax.swing.JComboBox<String> cbSearchChoice1;
    private javax.swing.JComboBox<String> cbSearchChoice2;
    private javax.swing.JComboBox<String> cbSearchChoice3;
    private javax.swing.JComboBox<String> cbSearchChoice4;
    private com.toedter.calendar.JDateChooser dcDOB;
    private javax.swing.JLabel iconBookShelf;
    private javax.swing.JLabel iconNotification;
    private javax.swing.JLabel iconRedNotification;
    private javax.swing.JLabel iconYourRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBookCode;
    private javax.swing.JLabel lblBookRequest;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblMARC;
    private javax.swing.JLabel lblManageBook;
    private javax.swing.JLabel lblManageLibrarian;
    private javax.swing.JLabel lblManageReader;
    private javax.swing.JLabel lblManageReaderCard;
    private javax.swing.JLabel lblMenuBG;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNotification;
    private javax.swing.JLabel lblNumberNotification;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JTable tblBook;
    private javax.swing.JTable tblBookCopy;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNotes;
    private javax.swing.JTextField txtPhoneNum;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    private javax.swing.JTextField txtSearchBook;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTopic;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
