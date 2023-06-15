// co sua
package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.KhachHang;
import util.MaHoa;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khachhang";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhacHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaTen = rs.getString("hoten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBangTin = rs.getBoolean("dangkinhanbangtin");
				String maXacThuc = rs.getString("maxacthuc");
				Date thoiGianHieuLucMaXacThuc = rs.getDate("thoigianhieulucmaxacthuc");
				boolean trangThaiXacThuc = rs.getBoolean("trangthaixacthuc");
				String duongDanAnh = rs.getString("duongdananh");

				KhachHang kh = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, 
						ngaySinh, soDienThoai, email, dangKyNhanBangTin, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
				ketQua.add(kh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public KhachHang selectById(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khachhang WHERE makhachhang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhacHang());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhacHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaTen = rs.getString("hoten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBangTin = rs.getBoolean("dangkinhanbangtin");
				String maXacThuc = rs.getString("maxacthuc");
				Date thoiGianHieuLucMaXacThuc = rs.getDate("thoigianhieulucmaxacthuc");
				boolean trangThaiXacThuc = rs.getBoolean("trangthaixacthuc");
				String duongDanAnh = rs.getString("duongdananh");

				ketQua = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, 
						ngaySinh, soDienThoai, email, dangKyNhanBangTin, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public KhachHang selectByUsernameAndPassword(String username, String password) {
		KhachHang ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khachhang WHERE tendangnhap=? and matkhau=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhacHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaTen = rs.getString("hoten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBangTin = rs.getBoolean("dangkinhanbangtin");
				String maXacThuc = rs.getString("maxacthuc");
				Date thoiGianHieuLucMaXacThuc = rs.getDate("thoigianhieulucmaxacthuc");
				boolean trangThaiXacThuc = rs.getBoolean("trangthaixacthuc");
				String duongDanAnh = rs.getString("duongdananh");

				ketQua = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, 
						ngaySinh, soDienThoai, email, dangKyNhanBangTin, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insert(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO khachhang (makhachhang, tendangnhap, matkhau, email, maxacthuc, thoigianhieulucmaxacthuc, trangthaixacthuc) "
					+ " VALUES (?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhacHang());
			st.setString(2, t.getTenDangNhap());
			st.setString(3, t.getMatKhau());
			st.setString(4, t.getEmail());
			st.setString(5, t.getMaXacThuc());
			st.setDate(6, t.getThoiGianHieuLucMaXacThuc());
			st.setBoolean(7, t.isTrangThaiXacThuc());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> arr) {
		int dem = 0;
		for (KhachHang KhachHang : arr) {
			dem += this.insert(KhachHang);
		}
		return dem;
	}

	@Override
	public int delete(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from khachhang " + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhacHang());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> arr) {
		int dem = 0;
		for (KhachHang KhachHang : arr) {
			dem += this.delete(KhachHang);
		}
		return dem;
	}

	@Override
	public int update(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang " + " SET " + " tendangnhap=?" + ", matkhau=?" + ", hoten=?" + ", gioitinh=?"
					+ ", diachi=?" + ", diachinhanhang=?" + ", diachimuahang=?" + ", ngaysinh=?" + ", sodienthoai=?"
					+ ", email=?" + ", dangkinhanbangtin=?" + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());
			st.setString(3, t.getHoVaTen());
			st.setString(4, t.getGioiTinh());
			st.setString(5, t.getDiaChi());
			st.setString(6, t.getDiaChiNhanHang());
			st.setString(7, t.getDiaChiMuaHang());
			st.setDate(8, t.getNgaySinh());
			st.setString(9, t.getSoDienThoai());
			st.setString(10, t.getEmail());
			st.setBoolean(11, t.isDangKyNhanBangTin());
			st.setString(12, t.getMaKhacHang());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int updateById(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang "
					+ " SET " + " hoten=?" + ", gioitinh=?"
					+ ", diachi=?" + ", ngaysinh=?" + ", sodienthoai=?"
					+ ", email=?" + ", duongdananh=?"
					+ " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setString(2, t.getGioiTinh());
			st.setString(3, t.getDiaChi());
			st.setDate(4, t.getNgaySinh());
			st.setString(5, t.getSoDienThoai());
			st.setString(6, t.getEmail());
			st.setString(7, t.getDuongDanAnh());
			st.setString(8, t.getMaKhacHang());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int updatePassword(String t, String id) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang " + " SET " + "matkhau=?" + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			String hashPassword = MaHoa.toMD5(t);
			st.setString(1, hashPassword);
			st.setString(2, id);
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int updateConfirmAccount(String id) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang " + " SET " + "trangthaixacthuc=?" + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 1);
			st.setString(2, id);
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public boolean checkExistOfUsername(String tenDangNhap, String email) {
		boolean ketQua = false;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khachhang WHERE tendangnhap=? AND email=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, tenDangNhap);
			st.setString(2, email);

			// Bước 3: thực thi câu lệnh SQL
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				ketQua = true;
				break;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ketQua == true) {
			System.out.println("có trùng");
		}
		else {
			System.out.println("không trùng");
		}

		return ketQua;
	}
}