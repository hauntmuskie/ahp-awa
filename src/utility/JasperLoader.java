package utility;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Utility class for loading and displaying Jasper Reports.
 * Handles report loading, parameter management, and report display logic.
 * 
 * @author Siti Mawaddah
 */
public class JasperLoader {
    
    private Connection conn;
    private static final String IMAGE_PATH_KEY = "PATH_IMG";
    private static final String DEFAULT_IMAGE_PATH = "src/image/";
    
    /**
     * Constructor that initializes database connection
     */
    public JasperLoader() {
        this.conn = (Connection) new Koneksi().connect();
    }
    
    /**
     * Constructor with custom database connection
     * @param connection Custom database connection
     */
    public JasperLoader(Connection connection) {
        this.conn = connection;
    }
    
    /**
     * Gets the absolute path for the image directory
     * @return The absolute path to the image directory
     */
    private String getImagePath() {
        try {
            File imageDir = new File(DEFAULT_IMAGE_PATH);
            return imageDir.getAbsolutePath() + File.separator;
        } catch (Exception e) {
            System.err.println("Error getting image path: " + e.getMessage());
            return "";
        }
    }
    
    /**
     * Shows a Jasper report with the given parameters
     * @param reportPath The path to the .jasper file
     * @param parameters Additional parameters for the report
     * @param reportName Name of the report for logging/error messages
     */
    private void showReport(String reportPath, Map<String, Object> parameters, String reportName) {
        System.out.println("=== " + reportName.toUpperCase() + " CLICKED ===");
        
        try {
            System.out.println("Loading report: " + reportName);
            
            // Setup base parameters
            HashMap<String, Object> reportParams = new HashMap<>();
            reportParams.put(IMAGE_PATH_KEY, getImagePath());
            
            // Add custom parameters if provided
            if (parameters != null) {
                reportParams.putAll(parameters);
            }
            
            System.out.println("Image path: " + getImagePath());
            
            File reportFile = new File(reportPath);
            System.out.println("Report file path: " + reportFile.getAbsolutePath());
            
            // Check if report file exists
            if (!reportFile.exists()) {
                throw new Exception("Report file not found: " + reportFile.getAbsolutePath());
            }
            
            @SuppressWarnings("deprecation")
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, conn);
            
            // Show the report
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            
            System.out.println("Report loaded successfully: " + reportName);
            
        } catch (Exception e) {
            System.err.println("ERROR - Failed to load " + reportName + ": " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Gagal memuat laporan " + reportName + ": " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("=== END " + reportName.toUpperCase() + " ===");
    }
    
    /**
     * Shows the Supplier Data Report
     */
    public void showLaporanDataSupplier() {
        String reportPath = "src/laporan/LaporanSupplier.jasper";
        showReport(reportPath, null, "Laporan Data Supplier");
    }
    
    /**
     * Shows the Criteria Priority Report
     */
    public void showLaporanPrioritasKriteria() {
        String reportPath = "src/laporan/LaporanPrioritasKriteria.jasper";
        showReport(reportPath, null, "Laporan Prioritas Kriteria");
    }
    
    /**
     * Shows the Sub Criteria Priority Report
     */
    public void showLaporanPrioritasSubKriteria() {
        String reportPath = "src/laporan/LaporanPrioritasSubKriteria.jasper";
        showReport(reportPath, null, "Laporan Prioritas Sub Kriteria");
    }
    
    /**
     * Shows the Selection Result Report
     */
    public void showLaporanHasilSeleksi() {
        String reportPath = "src/laporan/LaporanHasilSeleksi.jasper";
        showReport(reportPath, null, "Laporan Hasil Seleksi");
    }
    
    /**
     * Shows a report with custom parameters
     * @param reportPath Path to the .jasper file
     * @param parameters Custom parameters for the report
     * @param reportName Name of the report
     */
    public void showCustomReport(String reportPath, Map<String, Object> parameters, String reportName) {
        showReport(reportPath, parameters, reportName);
    }
    
    /**
     * Shows supplier report with filtering parameters
     * @param supplierId Supplier ID filter (use % for wildcard)
     * @param supplierName Supplier name filter (use % for wildcard)
     * @param supplierContact Supplier contact filter (use % for wildcard)
     * @param supplierAddress Supplier address filter (use % for wildcard)
     * @param supplierEmail Supplier email filter (use % for wildcard)
     */
    public void showLaporanDataSupplierWithFilter(String supplierId, String supplierName, 
                                                  String supplierContact, String supplierAddress, 
                                                  String supplierEmail) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("supplierId", wrapLikeParam(supplierId));
        parameters.put("supplierName", wrapLikeParam(supplierName));
        parameters.put("supplierContact", wrapLikeParam(supplierContact));
        parameters.put("supplierAddress", wrapLikeParam(supplierAddress));
        parameters.put("supplierEmail", wrapLikeParam(supplierEmail));
        
        String reportPath = "src/laporan/LaporanSupplier.jasper";
        showReport(reportPath, parameters, "Laporan Data Supplier (Filtered)");
    }
    
    /**
     * Wraps a parameter value with SQL LIKE wildcards if not already present
     * @param param The parameter value to wrap
     * @return The parameter wrapped with % wildcards
     */
    private String wrapLikeParam(String param) {
        if (param == null || param.trim().isEmpty()) {
            return "%";
        }
        
        String trimmed = param.trim();
        if (!trimmed.startsWith("%") && !trimmed.endsWith("%")) {
            return "%" + trimmed + "%";
        }
        
        return trimmed;
    }
    
    /**
     * Closes the database connection
     */
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed successfully");
            }
        } catch (Exception e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
    
    /**
     * Gets the current database connection
     * @return The current database connection
     */
    public Connection getConnection() {
        return this.conn;
    }
    
    /**
     * Sets a new database connection
     * @param connection The new database connection
     */
    public void setConnection(Connection connection) {
        this.conn = connection;
    }
}
