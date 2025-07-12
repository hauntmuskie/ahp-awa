
package menuutama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author Siti Mawaddah
 */
public class PengaturanSubKriteria extends javax.swing.JPanel {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form Pengaturan
     */
    public PengaturanSubKriteria() {
        initComponents();
        updateDataTabel();
    }
    
    protected void kosong(){
        cbSubKualitas1.setSelectedIndex(0);
        cbSubKualitas2.setSelectedIndex(0);
        cbSubKualitas3.setSelectedIndex(0);
        cbSubKualitas4.setSelectedIndex(0);
        cbSubKualitas5.setSelectedIndex(0);
        cbSubHarga1.setSelectedIndex(0);
        cbSubHarga2.setSelectedIndex(0);
        cbSubHarga3.setSelectedIndex(0);
        cbSubHarga4.setSelectedIndex(0);
        cbSubHarga5.setSelectedIndex(0);
        cbSubLayanan1.setSelectedIndex(0);
        cbSubLayanan2.setSelectedIndex(0);
        cbSubLayanan3.setSelectedIndex(0);
        cbSubLayanan4.setSelectedIndex(0);
        cbSubLayanan5.setSelectedIndex(0);
        cbSubPengiriman1.setSelectedIndex(0);
        cbSubPengiriman2.setSelectedIndex(0);
        cbSubPengiriman3.setSelectedIndex(0);
        cbSubPengiriman4.setSelectedIndex(0);
        cbSubPengiriman5.setSelectedIndex(0);
        cbSubJumlah1.setSelectedIndex(0);
        cbSubJumlah2.setSelectedIndex(0);
        cbSubJumlah3.setSelectedIndex(0);
        cbSubJumlah4.setSelectedIndex(0);
        cbSubJumlah5.setSelectedIndex(0);
    }
    
    protected void updateDataTabel(){
        Object[] Baris = {
            "Kode Kriteria",
            "Nama Kriteria",
            "Nama Sub Kriteria",
            "Kepentingan Sub Kriteria"
        };
        tabmode = new DefaultTableModel(null, Baris);
        tabelSubKriteria.setModel(tabmode);
        String sql = "SELECT * FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kd_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("nama_sub_kriteria");
                String d = hasil.getString("prioritas_kepentingan");
                
                String[] data={a, b, c, d};
                tabmode.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void getDataTabel(){
        String sql = "SELECT nama_sub_kriteria FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        int n = 1;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_sub_kriteria");
                if(n==1){
                    cbSubKualitas1.setSelectedItem(a);
                }else if(n==2){
                    cbSubKualitas2.setSelectedItem(a);
                }else if(n==3){
                    cbSubKualitas3.setSelectedItem(a);
                }else if(n==4){
                    cbSubKualitas4.setSelectedItem(a);
                }else if(n==5){
                    cbSubKualitas5.setSelectedItem(a);
                }else if(n==6){
                    cbSubHarga1.setSelectedItem(a);
                }else if(n==7){
                    cbSubHarga2.setSelectedItem(a);
                }else if(n==8){
                    cbSubHarga3.setSelectedItem(a);
                }else if(n==9){
                    cbSubHarga4.setSelectedItem(a);
                }else if(n==10){
                    cbSubHarga5.setSelectedItem(a);
                }else if(n==11){
                    cbSubLayanan1.setSelectedItem(a);
                }else if(n==12){
                    cbSubLayanan2.setSelectedItem(a);
                }else if(n==13){
                    cbSubLayanan3.setSelectedItem(a);
                }else if(n==14){
                    cbSubLayanan4.setSelectedItem(a);
                }else if(n==15){
                    cbSubLayanan5.setSelectedItem(a);
                }else if(n==16){
                    cbSubPengiriman1.setSelectedItem(a);
                }else if(n==17){
                    cbSubPengiriman2.setSelectedItem(a);
                }else if(n==18){
                    cbSubPengiriman3.setSelectedItem(a);
                }else if(n==19){
                    cbSubPengiriman4.setSelectedItem(a);
                }else if(n==20){
                    cbSubPengiriman5.setSelectedItem(a);
                }else if(n==21){
                    cbSubJumlah1.setSelectedItem(a);
                }else if(n==22){
                    cbSubJumlah2.setSelectedItem(a);
                }else if(n==23){
                    cbSubJumlah3.setSelectedItem(a);
                }else if(n==24){
                    cbSubJumlah4.setSelectedItem(a);
                }else if(n==25){
                    cbSubJumlah5.setSelectedItem(a);
                } 
                n++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //masukan data subkriteria
    protected void insertDataSubKriteria(){
        try{
        int n=1, nKualitas=1, nHarga=1, nLayanan=1, nKetepatanPengiriman=1, nKetepatanJumlah=1, i=1;
            do{
                String kepentingan;
                String sql = "INSERT INTO sub_kriteria (no_sub, kd_kriteria, nama_kriteria, nama_sub_kriteria, prioritas_kepentingan) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlKualitas = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Kualitas'";
                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                String sqlLayanan = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Layanan'";
                String sqlKetepatanPengiriman = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketepatan Pengiriman'";
                String sqlKetepatanJumlah = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketepatan Jumlah'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlKualitas);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nKualitas == 1){
                        stat.setString(4, cbSubKualitas1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKualitas == 2){
                        stat.setString(4, cbSubKualitas2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKualitas == 3){
                        stat.setString(4, cbSubKualitas3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKualitas == 4){
                        stat.setString(4, cbSubKualitas4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(4, cbSubKualitas5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nKualitas++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHarga);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nHarga == 1){
                        stat.setString(4, cbSubHarga1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHarga == 2){
                        stat.setString(4, cbSubHarga2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nHarga == 3){
                        stat.setString(4, cbSubHarga3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nHarga == 4){
                        stat.setString(4, cbSubHarga4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(4, cbSubHarga5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nHarga++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlLayanan);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nLayanan == 1){
                        stat.setString(4, cbSubLayanan1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nLayanan == 2){
                        stat.setString(4, cbSubLayanan2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nLayanan == 3){
                        stat.setString(4, cbSubLayanan3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nLayanan == 4){
                        stat.setString(4, cbSubLayanan4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(4, cbSubLayanan5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nLayanan++;
                }else if(n==4){
                    hasil = statCek.executeQuery(sqlKetepatanPengiriman);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nKetepatanPengiriman == 1){
                        stat.setString(4, cbSubPengiriman1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKetepatanPengiriman == 2){
                        stat.setString(4, cbSubPengiriman2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKetepatanPengiriman == 3){
                        stat.setString(4, cbSubPengiriman3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKetepatanPengiriman == 4){
                        stat.setString(4, cbSubPengiriman4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(4, cbSubPengiriman5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nKetepatanPengiriman++;
                }else{    
                    hasil = statCek.executeQuery(sqlKetepatanJumlah);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nKetepatanJumlah == 1){
                        stat.setString(4, cbSubJumlah1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKetepatanJumlah == 2){
                        stat.setString(4, cbSubJumlah2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKetepatanJumlah == 3){
                        stat.setString(4, cbSubJumlah3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKetepatanJumlah == 4){
                        stat.setString(4, cbSubJumlah4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(4, cbSubJumlah5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nKetepatanJumlah++;
                }
            }while(n<=5);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }
    
    protected void hapusDataSubKriteria(){
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM sub_kriteria";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                kosong();
                updateDataTabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal diHapus " + e);
            }
        }
    }
    
    protected void editDataSubKriteria(){
        try{
        int n=1, nKualitas=1, nHarga=1, nLayanan=1, nKetepatanPengiriman=1, nKetepatanJumlah=1, i=1;
            do{
                String kepentingan;
                String sql = "UPDATE sub_kriteria set kd_kriteria=?, nama_kriteria=?, nama_sub_kriteria=?, prioritas_kepentingan=? WHERE no_sub=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlKualitas = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Kualitas'";
                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                String sqlLayanan = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Layanan'";
                String sqlKetepatanPengiriman = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketepatan Pengiriman'";
                String sqlKetepatanJumlah = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Ketepatan Jumlah'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlKualitas);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nKualitas == 1){
                        stat.setString(3, cbSubKualitas1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKualitas == 2){
                        stat.setString(3, cbSubKualitas2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKualitas == 3){
                        stat.setString(3, cbSubKualitas3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKualitas == 4){
                        stat.setString(3, cbSubKualitas4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(3, cbSubKualitas5.getSelectedItem().toString());
                        kepentingan="Biasa ke-5";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nKualitas++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHarga);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nHarga == 1){
                        stat.setString(3, cbSubHarga1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHarga == 2){
                        stat.setString(3, cbSubHarga2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nHarga == 3){
                        stat.setString(3, cbSubHarga3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nHarga == 4){
                        stat.setString(3, cbSubHarga4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(3, cbSubHarga5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nHarga++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlLayanan);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nLayanan == 1){
                        stat.setString(3, cbSubLayanan1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nLayanan == 2){
                        stat.setString(3, cbSubLayanan2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nLayanan == 3){
                        stat.setString(3, cbSubLayanan3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nLayanan == 4){
                        stat.setString(3, cbSubLayanan4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(3, cbSubLayanan5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nLayanan++;
                }else if(n==4){
                    hasil = statCek.executeQuery(sqlKetepatanPengiriman);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nKetepatanPengiriman == 1){
                        stat.setString(3, cbSubPengiriman1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKetepatanPengiriman == 2){
                        stat.setString(3, cbSubPengiriman2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKetepatanPengiriman == 3){
                        stat.setString(3, cbSubPengiriman3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKetepatanPengiriman == 4){
                        stat.setString(3, cbSubPengiriman4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(3, cbSubPengiriman5.getSelectedItem().toString());
                        kepentingan="Kurang Penting ke-5";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nKetepatanPengiriman++;
                }else{    
                    hasil = statCek.executeQuery(sqlKetepatanJumlah);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nKetepatanJumlah == 1){
                        stat.setString(3, cbSubJumlah1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKetepatanJumlah == 2){
                        stat.setString(3, cbSubJumlah2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nKetepatanJumlah == 3){
                        stat.setString(3, cbSubJumlah3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else if(nKetepatanJumlah == 4){
                        stat.setString(3, cbSubJumlah4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                    }else{
                        stat.setString(3, cbSubJumlah5.getSelectedItem().toString());
                        kepentingan="Biasa ke-5";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nKetepatanJumlah++;
                }
            }while(n<=5);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil DiUbah");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal DiUbah "+e);
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

        judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSubKriteria = new javax.swing.JTable();
        tombolSimpan = new javax.swing.JButton();
        tombolEdit = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbSubKualitas1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbSubKualitas2 = new javax.swing.JComboBox<>();
        cbSubKualitas3 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbSubKualitas4 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbSubKualitas5 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbSubHarga1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbSubHarga2 = new javax.swing.JComboBox<>();
        cbSubHarga3 = new javax.swing.JComboBox<>();
        cbSubHarga4 = new javax.swing.JComboBox<>();
        cbSubHarga5 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbSubLayanan1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cbSubLayanan2 = new javax.swing.JComboBox<>();
        cbSubLayanan3 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbSubLayanan4 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cbSubLayanan5 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        cbSubPengiriman1 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        cbSubPengiriman2 = new javax.swing.JComboBox<>();
        cbSubPengiriman3 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        cbSubPengiriman4 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cbSubPengiriman5 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cbSubJumlah1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cbSubJumlah2 = new javax.swing.JComboBox<>();
        cbSubJumlah3 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cbSubJumlah4 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        cbSubJumlah5 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 237, 192));
        setPreferredSize(new java.awt.Dimension(990, 700));

        judul.setBackground(new java.awt.Color(22, 65, 53));
        judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 237, 192));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText("  Pengaturan Bobot Kepentingan Sub Kriteria");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 237, 192));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 620));

        tabelSubKriteria.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelSubKriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Nama Sub Kriteria", "Kepentingan Sub Kriteria"
            }
        ));
        tabelSubKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSubKriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSubKriteria);

        tombolSimpan.setBackground(new java.awt.Color(22, 65, 53));
        tombolSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolSimpan.setForeground(new java.awt.Color(255, 237, 192));
        tombolSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Simpan Small.png"))); // NOI18N
        tombolSimpan.setText("SIMPAN");
        tombolSimpan.setBorder(null);
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        tombolEdit.setBackground(new java.awt.Color(22, 65, 53));
        tombolEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 237, 192));
        tombolEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Ubah Small.png"))); // NOI18N
        tombolEdit.setText("UBAH");
        tombolEdit.setBorder(null);
        tombolEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolEditActionPerformed(evt);
            }
        });

        tombolHapus.setBackground(new java.awt.Color(22, 65, 53));
        tombolHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolHapus.setForeground(new java.awt.Color(255, 237, 192));
        tombolHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hapus Small.png"))); // NOI18N
        tombolHapus.setText("HAPUS");
        tombolHapus.setBorder(null);
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Catatan : Edit data, klik data pada tabel terlebih dahulu");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(22, 65, 53));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kualitas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 237, 192));
        jLabel12.setText("Kualitas Sangat Penting ke-1");

        cbSubKualitas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Kualitas -", "Sangat Baik", "Baik", "Cukup", "Kurang", "Sangat Kurang" }));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 237, 192));
        jLabel13.setText("Kualitas Penting ke-2");

        cbSubKualitas2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Kualitas -", "Sangat Baik", "Baik", "Cukup", "Kurang", "Sangat Kurang" }));

        cbSubKualitas3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Kualitas -", "Sangat Baik", "Baik", "Cukup", "Kurang", "Sangat Kurang" }));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 237, 192));
        jLabel14.setText("Kualitas Cukup Penting ke-3");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 237, 192));
        jLabel15.setText("Kualitas Biasa ke-4");

        cbSubKualitas4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Kualitas -", "Sangat Baik", "Baik", "Cukup", "Kurang", "Sangat Kurang" }));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 237, 192));
        jLabel16.setText("Kualitas Kurang Penting ke-5");

        cbSubKualitas5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Kualitas -", "Sangat Baik", "Baik", "Cukup", "Kurang", "Sangat Kurang" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSubKualitas2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubKualitas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubKualitas4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubKualitas5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubKualitas3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbSubKualitas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbSubKualitas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbSubKualitas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbSubKualitas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbSubKualitas5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBackground(new java.awt.Color(22, 65, 53));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Harga", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 237, 192));
        jLabel17.setText("Harga Sangat Penting ke-1");

        cbSubHarga1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Harga -", "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 237, 192));
        jLabel18.setText("Harga Penting ke-2");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 237, 192));
        jLabel19.setText("Harga Cukup Penting ke-3");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 237, 192));
        jLabel20.setText("Harga Biasa ke-4");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 237, 192));
        jLabel21.setText("Harga Kurang Penting ke-5");

        cbSubHarga2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Harga -", "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

        cbSubHarga3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Harga -", "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

        cbSubHarga4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Harga -", "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

        cbSubHarga5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Harga -", "Sangat Terjangkau", "Terjangkau", "Normal", "Mahal", "Sangat Mahal" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubHarga1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 175, Short.MAX_VALUE)
                    .addComponent(cbSubHarga2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga5, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbSubHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubHarga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubHarga3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubHarga4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubHarga5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)))
        );

        jPanel5.setBackground(new java.awt.Color(22, 65, 53));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Layanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 237, 192));
        jLabel22.setText("Layanan Sangat Penting ke-1");

        cbSubLayanan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Layanan -", "Sangat Memuaskan", "Memuaskan", "Cukup", "Kurang Memuaskan", "Tidak Memuaskan" }));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 237, 192));
        jLabel23.setText("Layanan Penting ke-2");

        cbSubLayanan2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Layanan -", "Sangat Memuaskan", "Memuaskan", "Cukup", "Kurang Memuaskan", "Tidak Memuaskan" }));
        cbSubLayanan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubLayanan2ActionPerformed(evt);
            }
        });

        cbSubLayanan3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Layanan -", "Sangat Memuaskan", "Memuaskan", "Cukup", "Kurang Memuaskan", "Tidak Memuaskan" }));

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 237, 192));
        jLabel24.setText("Layanan Cukup Penting ke-3");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 237, 192));
        jLabel27.setText("Layanan Biasa ke-4");

        cbSubLayanan4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Layanan -", "Sangat Memuaskan", "Memuaskan", "Cukup", "Kurang Memuaskan", "Tidak Memuaskan" }));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 237, 192));
        jLabel28.setText("Layanan Kurang Penting ke-5");

        cbSubLayanan5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Layanan -", "Sangat Memuaskan", "Memuaskan", "Cukup", "Kurang Memuaskan", "Tidak Memuaskan" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubLayanan5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubLayanan4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubLayanan3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubLayanan2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubLayanan1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbSubLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbSubLayanan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbSubLayanan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbSubLayanan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbSubLayanan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBackground(new java.awt.Color(22, 65, 53));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ketepatan Pengiriman", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 237, 192));
        jLabel29.setText("Pengiriman Sangat Penting ke-1");

        cbSubPengiriman1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Pengiriman -", "Sangat Tepat Waktu", "Tepat Waktu", "Cukup Tepat", "Terlambat", "Sangat Terlambat" }));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 237, 192));
        jLabel30.setText("Pengiriman Penting ke-2");

        cbSubPengiriman2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Pengiriman -", "Sangat Tepat Waktu", "Tepat Waktu", "Cukup Tepat", "Terlambat", "Sangat Terlambat" }));

        cbSubPengiriman3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Pengiriman -", "Sangat Tepat Waktu", "Tepat Waktu", "Cukup Tepat", "Terlambat", "Sangat Terlambat" }));

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 237, 192));
        jLabel37.setText("Pengiriman Cukup Penting ke-3");

        cbSubPengiriman4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Pengiriman -", "Sangat Tepat Waktu", "Tepat Waktu", "Cukup Tepat", "Terlambat", "Sangat Terlambat" }));

        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 237, 192));
        jLabel38.setText("Pengiriman Biasa ke-4");

        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 237, 192));
        jLabel39.setText("Pengiriman Kurang Penting ke-5");

        cbSubPengiriman5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Pengiriman -", "Sangat Tepat Waktu", "Tepat Waktu", "Cukup Tepat", "Terlambat", "Sangat Terlambat" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubPengiriman1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubPengiriman2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubPengiriman3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubPengiriman4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubPengiriman5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbSubPengiriman1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbSubPengiriman2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cbSubPengiriman3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cbSubPengiriman4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(cbSubPengiriman5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.setBackground(new java.awt.Color(22, 65, 53));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ketepatan Jumlah", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 237, 192));
        jLabel33.setText("Jumlah Sangat Penting ke-1");

        cbSubJumlah1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Jumlah -", "Sangat Sesuai", "Sesuai", "Cukup Sesuai", "Kurang Sesuai", "Tidak Sesuai" }));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 237, 192));
        jLabel34.setText("Jumlah Penting ke-2");

        cbSubJumlah2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Jumlah -", "Sangat Sesuai", "Sesuai", "Cukup Sesuai", "Kurang Sesuai", "Tidak Sesuai" }));

        cbSubJumlah3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Jumlah -", "Sangat Sesuai", "Sesuai", "Cukup Sesuai", "Kurang Sesuai", "Tidak Sesuai" }));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 237, 192));
        jLabel35.setText("Jumlah Cukup Penting ke-3");

        cbSubJumlah4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Jumlah -", "Sangat Sesuai", "Sesuai", "Cukup Sesuai", "Kurang Sesuai", "Tidak Sesuai" }));

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 237, 192));
        jLabel36.setText("Jumlah Biasa ke-4");

        cbSubJumlah5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Sub Kriteria Jumlah -", "Sangat Sesuai", "Sesuai", "Cukup Sesuai", "Kurang Sesuai", "Tidak Sesuai" }));

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 237, 192));
        jLabel40.setText("Jumlah Kurang Penting ke-5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubJumlah1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubJumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubJumlah3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubJumlah4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbSubJumlah5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cbSubJumlah1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cbSubJumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbSubJumlah3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cbSubJumlah4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(cbSubJumlah5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(958, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Kualitas");

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        insertDataSubKriteria();
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tombolEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolEditActionPerformed
        editDataSubKriteria();
    }//GEN-LAST:event_tombolEditActionPerformed

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        hapusDataSubKriteria();
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tabelSubKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSubKriteriaMouseClicked
        getDataTabel();
    }//GEN-LAST:event_tabelSubKriteriaMouseClicked

    private void cbSubLayanan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubLayanan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubLayanan2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbSubHarga1;
    private javax.swing.JComboBox<String> cbSubHarga2;
    private javax.swing.JComboBox<String> cbSubHarga3;
    private javax.swing.JComboBox<String> cbSubHarga4;
    private javax.swing.JComboBox<String> cbSubHarga5;
    private javax.swing.JComboBox<String> cbSubJumlah1;
    private javax.swing.JComboBox<String> cbSubJumlah2;
    private javax.swing.JComboBox<String> cbSubJumlah3;
    private javax.swing.JComboBox<String> cbSubJumlah4;
    private javax.swing.JComboBox<String> cbSubJumlah5;
    private javax.swing.JComboBox<String> cbSubKualitas1;
    private javax.swing.JComboBox<String> cbSubKualitas2;
    private javax.swing.JComboBox<String> cbSubKualitas3;
    private javax.swing.JComboBox<String> cbSubKualitas4;
    private javax.swing.JComboBox<String> cbSubKualitas5;
    private javax.swing.JComboBox<String> cbSubLayanan1;
    private javax.swing.JComboBox<String> cbSubLayanan2;
    private javax.swing.JComboBox<String> cbSubLayanan3;
    private javax.swing.JComboBox<String> cbSubLayanan4;
    private javax.swing.JComboBox<String> cbSubLayanan5;
    private javax.swing.JComboBox<String> cbSubPengiriman1;
    private javax.swing.JComboBox<String> cbSubPengiriman2;
    private javax.swing.JComboBox<String> cbSubPengiriman3;
    private javax.swing.JComboBox<String> cbSubPengiriman4;
    private javax.swing.JComboBox<String> cbSubPengiriman5;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelSubKriteria;
    private javax.swing.JButton tombolEdit;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables
}
