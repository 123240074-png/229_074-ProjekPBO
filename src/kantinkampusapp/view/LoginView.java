package kantinkampusapp.view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class LoginView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // Color palette
    private static final Color DARK_GREEN    = new Color(15, 36, 24);
    private static final Color MID_GREEN     = new Color(26, 61, 38);
    private static final Color AMBER         = new Color(217, 119, 6);
    private static final Color CREAM_TEXT    = new Color(245, 240, 232);
    private static final Color MUTED_GREEN   = new Color(122, 158, 131);
    private static final Color BG_WHITE      = new Color(250, 250, 250);
    private static final Color FIELD_BG      = new Color(241, 241, 241);
    private static final Color FIELD_BORDER  = new Color(200, 200, 200);
    private static final Color TEXT_MAIN     = new Color(30, 30, 30);
    private static final Color TEXT_MUTED    = new Color(120, 120, 120);
    private static final Color TEXT_LABEL    = new Color(80, 80, 80);

    public LoginView() {
        setTitle("Kantin Kampus — Login Kasir");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(760, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left panel (branding)
        JPanel leftPanel = createLeftPanel();
        leftPanel.setPreferredSize(new Dimension(310, 460));
        add(leftPanel, BorderLayout.WEST);

        // Right panel (form)
        JPanel rightPanel = createRightPanel();
        add(rightPanel, BorderLayout.CENTER);
    }

    // ───── LEFT PANEL ─────
    private JPanel createLeftPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background
                g2.setColor(DARK_GREEN);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Decorative circles
                g2.setColor(MID_GREEN);
                g2.fillOval(-50, -50, 160, 160);
                g2.fillOval(getWidth() - 130, getHeight() - 130, 220, 220);

                // Icon box (amber)
                int ix = 36, iy = 56;
                g2.setColor(AMBER);
                g2.fillRoundRect(ix, iy, 48, 48, 12, 12);
                // Simple fork+spoon icon via lines
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(ix + 16, iy + 10, ix + 16, iy + 38);
                g2.drawLine(ix + 16, iy + 10, ix + 13, iy + 18);
                g2.drawLine(ix + 16, iy + 10, ix + 19, iy + 18);
                g2.drawLine(ix + 32, iy + 10, ix + 32, iy + 22);
                g2.draw(new Ellipse2D.Double(ix + 27, iy + 10, 10, 10));
                g2.drawLine(ix + 32, iy + 22, ix + 32, iy + 38);

                // Title
                g2.setFont(new Font("Georgia", Font.BOLD, 28));
                g2.setColor(CREAM_TEXT);
                g2.drawString("Kantin", ix, iy + 82);
                g2.drawString("Kampus", ix, iy + 114);

                // Subtitle
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                g2.setColor(MUTED_GREEN);
                g2.drawString("Sistem kasir digital untuk", ix, iy + 144);
                g2.drawString("transaksi yang lebih cepat.", ix, iy + 162);

                // Footer
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                g2.setColor(new Color(74, 110, 84));
                g2.drawString("© 2026 KantinKampusApp", ix, getHeight() - 28);

                g2.dispose();
            }
        };
    }

    // ───── RIGHT PANEL ─────
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(null); // absolute layout for full control
        panel.setBackground(BG_WHITE);

        // Heading
        JLabel lblTitle = new JLabel("Selamat datang");
        lblTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitle.setForeground(TEXT_MAIN);
        lblTitle.setBounds(50, 60, 300, 30);
        panel.add(lblTitle);

        JLabel lblSub = new JLabel("Masuk ke akun kasir Anda");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(TEXT_MUTED);
        lblSub.setBounds(50, 94, 300, 20);
        panel.add(lblSub);

        // Username
        JLabel lblUser = new JLabel("USERNAME");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblUser.setForeground(TEXT_LABEL);
        lblUser.setBounds(50, 152, 200, 16);
        panel.add(lblUser);

        txtUsername = new JTextField();
        styleField(txtUsername);
        txtUsername.setBounds(50, 172, 320, 42);
        panel.add(txtUsername);

        // Password
        JLabel lblPass = new JLabel("PASSWORD");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblPass.setForeground(TEXT_LABEL);
        lblPass.setBounds(50, 230, 200, 16);
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        styleField(txtPassword);
        txtPassword.setBounds(50, 250, 320, 42);
        panel.add(txtPassword);

        // Login button
        btnLogin = new JButton("MASUK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? MID_GREEN
                        : getModel().isRollover() ? MID_GREEN : DARK_GREEN);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(CREAM_TEXT);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btnLogin.setOpaque(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(50, 316, 320, 46);
        btnLogin.addActionListener(this::doLogin);
        panel.add(btnLogin);

        // Enter key on password triggers login
        txtPassword.addActionListener(this::doLogin);

        return panel;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT_MAIN);
        field.setBackground(FIELD_BG);
        field.setCaretColor(DARK_GREEN);
        field.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, FIELD_BORDER),
            BorderFactory.createEmptyBorder(0, 14, 0, 14)
        ));

        // Highlight border on focus
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedBorder(10, AMBER),
                    BorderFactory.createEmptyBorder(0, 14, 0, 14)
                ));
            }
            @Override public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedBorder(10, FIELD_BORDER),
                    BorderFactory.createEmptyBorder(0, 14, 0, 14)
                ));
            }
        });
    }

    private void doLogin(ActionEvent evt) {
        try {
            java.sql.Connection conn = kantinkampusapp.database.Koneksi.getKoneksi();
            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtUsername.getText());
            pst.setString(2, new String(txtPassword.getPassword()));
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(null, "Login berhasil sebagai " + role);
                if (role.equals("admin")) {
                    new MenuView().setVisible(true);
                } else {
                    new DashboardView().setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username atau password salah", "Login Gagal", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ───── ROUNDED BORDER HELPER ─────
    static class RoundedBorder implements Border {
        private final int radius;
        private final Color color;
        RoundedBorder(int radius, Color color) { this.radius = radius; this.color = color; }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
            g2.dispose();
            // ← hapus fillRoundRect yang lama
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(8, 14, 8, 14); }
        @Override public boolean isBorderOpaque() { return false; }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
