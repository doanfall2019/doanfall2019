<?php 
	include_once("config.php");

	if(isset($_POST["ham"]))
	$ham=$_POST["ham"];


	switch ($ham) {
		case 'LayDanhSachMenu':
			$ham();
			break;

		case 'DangKyThanhVien':
			$ham();
			break;

		case 'KiemTraDangNhap':
			$ham();
			break;

		case 'LayDanhSachCacThuongHieuLon':
			$ham();
			break;

		case 'LayDanhSachTopDienThoaiMayTinhBang':
			$ham();
			break;

		case 'LayDanhSachTopPhuKien':
			$ham();
			break;

		case 'LayDanhSachPhuKien':
			$ham();
			break;

		case 'LayDanhSachTienIch':
			$ham();
			break;

		case 'LayTopTienIch':
			$ham();
			break;

		case 'LayDanhSachSanPhamTheoMaLoaiDanhMuc':
			$ham();
			break;

		case 'LayDanhSachSanPhamTheoMaThuongHieu':
			$ham();
			break;

		case 'TimKiemSanPhamTheoTenSP':
			$ham();
			break;

		case 'LaySanPhamVsChitietTheoMaSP':
			$ham();
			break;

		case 'LayDanhSachKhuyenMai':
			$ham();
			break;

		case 'ThemDanhGia':
			$ham(); 
			break;

		case 'ThemHoaDon':
			$ham(); 
			break;

		case 'LayDanhSachDanhGiaTheoMaSP':
			$ham(); 
			break;

		case 'LayDanhSachSanPhamTheoGiaTang':
			$ham(); 
			break;

	}

		function LayDanhSachSanPhamTheoGiaTang(){
		global $conn;
		$chuoijson = array();
		if(isset($_POST["maloaisp"]) || isset($_POST["limit"])){
			$maloai = $_POST["maloaisp"];
			$limit = $_POST["limit"];
		}
		
		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		$chuoijson = LayDanhSachSanPhamTheoGiaTienTang($conn,$maloai,$chuoijson,$limit);

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";

	}

		function LayDanhSachSanPhamTheoGiaTienTang($conn,$maloaith,$chuoijson,$limit){
		$ngayhientai = date("y/m/d");

			$truyvantienich="SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM thuonghieu th, sanpham sp, chitietkhuyenmai ctkm, khuyenmai km WHERE th.MATHUONGHIEU=".$maloaith." AND th.MATHUONGHIEU = sp.MATHUONGHIEU AND sp.MASP = ctkm.MASP AND km.MAKM = ctkm.MAKM ORDER BY sp.GIA LIMIT ".$limit.",20";
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					$phamtramkm = 0;

					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						$thoigiankm = $dongtienich["THOIHANKM"];

						if($thoigiankm > 0){
							$phamtramkm = $dongtienich["PHANTRAMKM"];
						}

						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "PHANTRAMKM"=>$dongtienich["PHANTRAMKM"],"TENSP"=>$dongtienich["TENSP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}

	function LayDanhSachDanhGiaTheoMaSP(){
		global $conn;
		$chuoijson = array();

		if(isset($_POST["masp"]) || isset($_POST["limit"]) ){
			$masp = $_POST["masp"];
			$limit = $_POST["limit"];
		}

		$truyvan = "SELECT * FROM danhgia WHERE MASP = ".$masp." ORDER BY NGAYDANHGIA LIMIT ".$limit." ,10";
		$ketqua = mysqli_query($conn,$truyvan);

		echo "{";
		echo "\"DANHSACHDANHGIA\":";

		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {
				$chuoijson[] = $dong;
			}
		}

		echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);

		echo "}";

	}

	function ThemDanhGia(){
		global $conn;

		if(isset($_POST["madg"]) || isset($_POST["masp"]) || isset($_POST["tenthietbi"]) || isset($_POST["tieude"]) || isset($_POST["noidung"]) || isset($_POST["sosao"]) ){
			$madg = $_POST["madg"];
			$masp = $_POST["masp"];
			$tenthietbi = $_POST["tenthietbi"];
			$tieude = $_POST["tieude"];
			$noidung = $_POST["noidung"];
			$sosao = $_POST["sosao"];
		}

		$ngaydang = date("d/m/Y");

		$truyvan = "INSERT INTO danhgia (MADG,MASP,TENTHIETBI,TIEUDE,NOIDUNG,SOSAO,NGAYDANHGIA) VALUES ('".$madg."', '".$masp."', '".$tenthietbi."', '".$tieude."', '".$noidung."', '".$sosao."', '".$ngaydang."' )";
		$ketqua = mysqli_query($conn,$truyvan);

		if($ketqua){
			echo "{ketqua:true}";
		}else{
			echo "{ketqua:false}";	
		}

	}

	function ThemHoaDon(){
		global $conn;

		if(isset($_POST["danhsachsanpham"]) || isset($_POST["tennguoinhan"]) || isset($_POST["sodt"]) || isset($_POST["diachi"]) || isset($_POST["chuyenkhoan"]) ){
			$danhsachsanpham = $_POST["danhsachsanpham"];
			$tennguoinhan = $_POST["tennguoinhan"];
			$sodt = $_POST["sodt"];
			$diachi = $_POST["diachi"];
			$chuyenkhoan = $_POST["chuyenkhoan"];
			
		}
		$ngayhientai = date("m/d/Y");
		$ngaygiao = date_create($ngayhientai);
		$ngaygiao = date_modify($ngaygiao,"+ 3 days");
		$ngaygiao = date_format($ngaygiao,"m/d/Y");
		
		$trangthai ="Chờ kiểm duyệt";


		
		$truyvan = "INSERT INTO hoadon (NGAYMUA,NGAYGIAO,TRANGTHAI,TENNGUOINHAN,SODT,DIACHI,CHUYENKHOAN) VALUES ('".$ngayhientai."', '".$ngaygiao."', '".$trangthai."', '".$tennguoinhan."', '".$sodt."', '".$diachi."', '".$chuyenkhoan."')";
		$ketqua = mysqli_query($conn,$truyvan);

		if($ketqua){
				
			$mahd = mysqli_insert_id($conn);
			$chuoijsonandroid = json_decode($danhsachsanpham);
			$arrayDanhSachSanPham = $chuoijsonandroid->DANHSACHSANPHAM;
			$dem = count($arrayDanhSachSanPham);

			for($i=0; $i<$dem; $i++){
				$jsonObect = $arrayDanhSachSanPham[$i];

				$masp = $jsonObect->masp;
				$soluong = $jsonObect->soluong;

				$truyvan = "INSERT INTO chitiethoadon (MAHD,MASP,SOLUONG) VALUES ('".$mahd."', '".$masp."', '".$soluong."')";
				$ketqua1 = mysqli_query($conn,$truyvan);


			}

			echo "{ketqua:true}" ;

		}else{
			echo "{ketqua:false}";	
		}

	}

	function LayDanhSachKhuyenMai(){
		global $conn;
		$chuoijson = array();
		$ngayhientai = date("y/m/d");

		$truyvan = "SELECT * FROM khuyenmai km, loaisanpham lsp WHERE DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') >=0  AND km.MALOAISP = lsp.MALOAISP";
		$ketqua = mysqli_query($conn,$truyvan);

		echo "{";
		echo "\"DANHSACHKHUYENMAI\":";

		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {
				$truyvanchitietkm = "SELECT * FROM chitietkhuyenmai ctkm, sanpham sp WHERE ctkm.MAKM='".$dong["MAKM"]."' AND ctkm.MASP=sp.MASP";
				$ketquakhuyenmai = mysqli_query($conn,$truyvanchitietkm);

				$chuoijsondanhsachsanpham = array();

				if($ketquakhuyenmai){
					while ($dongkhuyenmai = mysqli_fetch_array($ketquakhuyenmai)) {
						$chuoijsondanhsachsanpham[] = $dongkhuyenmai;
					}
				}

				array_push($chuoijson,array("MAKM"=>$dong["MAKM"],"TENLOAISP"=>$dong["TENLOAISP"],"TENKM"=>$dong["TENKM"],"HINHKHUYENMAI"=>$dong["HINHKHUYENMAI"], "DANHSACHSANPHAM"=>$chuoijsondanhsachsanpham));

			}
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";

	}

	function LaySanPhamVsChitietTheoMaSP(){
		global $conn;
		$chuoijson = array();
		$chuoijsonchitiet = array();
		if(isset($_POST["masp"])){
			$masp = $_POST["masp"];
		}

		$ngayhientai = date("y/m/d");

		$truyvan = "SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM sanpham sp, nguoidung nd, khuyenmai km, chitietkhuyenmai ctkm WHERE sp.MASP=".$masp. " AND sp.MANGUOIDUNG = nd.MANGUOIDUNG AND sp.MASP=ctkm.MASP AND km.MAKM=ctkm.MAKM" ;
		$ketqua = mysqli_query($conn, $truyvan);

		echo "{";
		echo "\"CHITIETSANPHAM\":";

		$truyvanchitiet = "SELECT * FROM chitietsanpham WHERE MASP=".$masp;
		$ketquachitiet = mysqli_query($conn, $truyvanchitiet);

		if($ketquachitiet){

			while ($dongchitiet = mysqli_fetch_array($ketquachitiet)) {
				array_push($chuoijsonchitiet, array($dongchitiet["TENCHITIET"]=>$dongchitiet["GIATRI"]));
			}
		}


		if($ketqua){
			$phamtramkm = 0;
			
			while ($dong = mysqli_fetch_array($ketqua)) {
				
				$thoigiankm = $dong["THOIHANKM"];

				if($thoigiankm > 0){
					$phamtramkm = $dong["PHANTRAMKM"];
				}

				array_push($chuoijson,array("MASP"=>$dong["MASP"],"TENSP"=>$dong["TENSP"],"GIATIEN"=>$dong["GIA"],"SOLUONG"=>$dong["SOLUONG"],"HINHSANPHAM"=>$dong["HINHLON"],"HINHSANPHAMNHO"=>$dong["HINHNHO"],"THONGTIN"=>$dong["THONGTIN"],"MALOAISP"=>$dong["MALOAISP"],"MATHUONGHIEU"=>$dong["MATHUONGHIEU"],"MANGUOIDUNG"=>$dong["MANGUOIDUNG"],"TENNGUOIDUNG"=>$dong["TENNGUOIDUNG"],"PHANTRAMKM"=>$dong["PHANTRAMKM"],"LUOTMUA"=>$dong["LUOTMUA"],"THONGSOKYTHUAT"=>$chuoijsonchitiet));
			}
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}



	function TimKiemSanPhamTheoTenSP(){
		global $conn;
		$chuoijson = array();

		if(isset($_POST["tensp"])|| isset($_POST["limit"])){
			$tensp = $_POST["tensp"];
			$limit = $_POST["limit"];
			
		}

		$ngayhientai = date("y/m/d");

		$truyvan = "SELECT * , DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM sanpham sp, khuyenmai km, chitietkhuyenmai ctkm WHERE sp.TENSP LIKE '%".$tensp."%' AND sp.MASP=ctkm.MASP AND km.MAKM=ctkm.MAKM ";
		

		$ketqua = mysqli_query($conn,$truyvan);

		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		if($ketqua){
			$phamtramkm = 0;

			while ($dong = mysqli_fetch_array($ketqua)) {
				
				$thoigiankm = $dong["THOIHANKM"];

				if($thoigiankm > 0){
					$phamtramkm = $dong["PHANTRAMKM"];
				}

				array_push($chuoijson,array("MASP"=>$dong["MASP"],"PHANTRAMKM"=>$dong["PHANTRAMKM"],"TENSP"=>$dong["TENSP"],"GIATIEN"=>$dong["GIA"],"HINHSANPHAM"=>$dong["HINHLON"],"HINHSANPHAMNHO"=>$dong["HINHNHO"]));
			}
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachSanPhamTheoMaThuongHieu(){
		global $conn;
		$chuoijson = array();
		if(isset($_POST["maloaisp"]) || isset($_POST["limit"])){
			$maloai = $_POST["maloaisp"];
			$limit = $_POST["limit"];
		}
		
		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		$chuoijson = LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloai,$chuoijson,$limit);

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";

	}

	function LayDanhSachSanPhamTheoMaLoaiDanhMuc(){
		global $conn;
		$chuoijson = array();

		if(isset($_POST["maloaisp"]) || isset($_POST["limit"])){
			$maloai = $_POST["maloaisp"];
			$limit = $_POST["limit"];
		}

		$chuoijson = LayDanhSachSanPhamDanhMucTheoMaLoai($conn,$maloai,$chuoijson,$limit);

		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayTopTienIch(){
		global $conn;

		$ketqua = LayDanhSachLOAISANPHAMTheoMaLoai($conn, 81);
		$chuoijson=array();

		echo "{";
		echo "\"TOPTIENICH\":";
		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				$ketquacon = LayDanhSachLOAISANPHAMTheoMaLoai($conn,$dong["MALOAISP"]);

				if ($ketquacon) {
					while ($dongcon=mysqli_fetch_array($ketquacon)) {
						$chuoijson = LayDanhSachSanPhamTheoMaLoai($conn,$dongcon["MALOAISP"], $chuoijson,10);
					}
				}
				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachTienIch(){
		global $conn;

		$ketqua = LayDanhSachLOAISANPHAMTheoMaLoai($conn, 81);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHTIENICH\":";
		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				$ketquacon = LayDanhSachLOAISANPHAMTheoMaLoai($conn,$dong["MALOAISP"]);

				if ($ketquacon) {
					while ($dongcon=mysqli_fetch_array($ketquacon)) {
						$chuoijson = LayDanhSachSanPhamTheoMaLoai($conn,$dongcon["MALOAISP"], $chuoijson,1);
					}
				}
				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachLOAISANPHAMTheoMaLoai($conn,$maloaisp){
		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.MALOAI_CHA =" .$maloaisp;
		$ketqua=mysqli_query($conn,$truyvancha);
	
		return $ketqua;
	}

		function LayDanhSachSanPhamDanhMucTheoMaLoai($conn,$maloaisp,$chuoijson,$limit){
			$truyvantienich="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAISP=".$maloaisp." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT ".$limit.",20";
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "TENSP"=>$dongtienich["TENSP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}




	function LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloaith,$chuoijson,$limit){
		$ngayhientai = date("y/m/d");

			$truyvantienich="SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM thuonghieu th, sanpham sp, chitietkhuyenmai ctkm, khuyenmai km WHERE th.MATHUONGHIEU=".$maloaith." AND th.MATHUONGHIEU = sp.MATHUONGHIEU AND sp.MASP = ctkm.MASP AND km.MAKM = ctkm.MAKM ORDER BY sp.GIA DESC LIMIT ".$limit.",20";
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					$phamtramkm = 0;

					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						$thoigiankm = $dongtienich["THOIHANKM"];

						if($thoigiankm > 0){
							$phamtramkm = $dongtienich["PHANTRAMKM"];
						}

						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "PHANTRAMKM"=>$dongtienich["PHANTRAMKM"],"TENSP"=>$dongtienich["TENSP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}

	function LayDanhSachSanPhamTheoMaLoai($conn,$maloaisp,$chuoijson,$limit){

		$ngayhientai = date("y/m/d");

			$truyvantienich="SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM loaisanpham lsp, sanpham sp, chitietkhuyenmai ctkm, khuyenmai km WHERE lsp.MALOAISP=".$maloaisp." AND lsp.MALOAISP = sp.MALOAISP AND sp.MASP = ctkm.MASP AND km.MAKM = ctkm.MAKM ORDER BY sp.LUOTMUA DESC LIMIT ".$limit;
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){

					$phamtramkm = 0;

					while ($dongtienich=mysqli_fetch_array($ketquacon)) {

						$thoigiankm = $dongtienich["THOIHANKM"];

						if($thoigiankm > 0){
							$phamtramkm = $dongtienich["PHANTRAMKM"];
						}


						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "TENSP"=>$dongtienich["TENLOAISP"], "GIATIEN"=>$dongtienich["GIA"],"PHANTRAMKM"=>$dongtienich["PHANTRAMKM"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}

	

	function LayDanhSachPhuKien(){
				global $conn;

		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%' ";
		$ketqua=mysqli_query($conn,$truyvancha);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHPHUKIEN\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				$truyvanphukiencon="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAI_CHA=".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
				$ketquacon=mysqli_query($conn,$truyvanphukiencon);

				if($ketquacon){
					while ($dongphukiencon=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongphukiencon["MALOAISP"], "TENSP"=>$dongphukiencon["TENLOAISP"], "GIATIEN"=>$dongphukiencon["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongphukiencon["HINHLON"])); 
					}
				}

				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}


	function LayDanhSachTopPhuKien(){
		global $conn;

		$ngayhientai = date("y/m/d");

		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%' ";
		$ketqua=mysqli_query($conn,$truyvancha);
		$chuoijson=array();

		echo "{";
		echo "\"TOPPHUKIEN\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				$truyvanphukiencon="SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM loaisanpham lsp, sanpham sp, chitietkhuyenmai ctkm, khuyenmai km WHERE lsp.MALOAI_CHA=".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP AND sp.MASP = ctkm.MASP AND km.MAKM = ctkm.MAKM ORDER BY sp.LUOTMUA DESC LIMIT 10 ";
				$ketquacon=mysqli_query($conn,$truyvanphukiencon);

				if($ketquacon){

					$phamtramkm = 0;

					while ($dongphukiencon=mysqli_fetch_array($ketquacon)) {

						$thoigiankm = $dongphukiencon["THOIHANKM"];

						if($thoigiankm > 0){
							$phamtramkm = $dongphukiencon["PHANTRAMKM"];
						}

						array_push($chuoijson, array("MASP"=>$dongphukiencon["MASP"], "TENSP"=>$dongphukiencon["TENSP"], "GIATIEN"=>$dongphukiencon["GIA"],"PHANTRAMKM"=>$dongphukiencon["PHANTRAMKM"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongphukiencon["HINHLON"])); 
					}
				}

				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}



	function LayDanhSachTopDienThoaiMayTinhBang(){
		global $conn;

		$ngayhientai = date("y/m/d");

		$truyvan="SELECT *, DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') AS THOIHANKM FROM loaisanpham lsp, sanpham sp, chitietkhuyenmai ctkm, khuyenmai km WHERE lsp.TENLOAISP LIKE 'điện thoại%' AND lsp.MALOAISP = sp.MALOAISP AND sp.MASP = ctkm.MASP AND km.MAKM = ctkm.MAKM ORDER BY sp.LUOTMUA DESC LIMIT 5";
		$ketqua=mysqli_query($conn,$truyvan);
		$chuoijson=array();

		echo "{";
		echo "\"TOPDIENTHOAIVAMAYTINHBANG\":";
		if ($ketqua) {

			$phamtramkm = 0;

			while ($dong=mysqli_fetch_array($ketqua)) {

				$thoigiankm = $dong["THOIHANKM"];

				if($thoigiankm > 0){
					$phamtramkm = $dong["PHANTRAMKM"];
				}

				array_push($chuoijson, array("MASP"=>$dong["MASP"], "TENSP"=>$dong["TENSP"], "GIATIEN"=>$dong["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dong["HINHLON"],"PHANTRAMKM"=>$dong["PHANTRAMKM"])); 
			}
			
		}


		$truyvan="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.TENLOAISP LIKE 'máy tính bảng%' AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 5";
		$ketquamtb=mysqli_query($conn,$truyvan);
		if ($ketquamtb) {
			while ($dongmtb=mysqli_fetch_array($ketquamtb)) {
				array_push($chuoijson, array("MASP"=>$dongmtb["MASP"], "TENSP"=>$dongmtb["TENSP"], "GIATIEN"=>$dongmtb["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongmtb["HINHLON"])); 
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachCacThuongHieuLon(){
		global $conn;

		$truyvan="SELECT * FROM thuonghieu th, chitietthuonghieu cth WHERE th.MATHUONGHIEU = cth.MATHUONGHIEU";
		$ketqua=mysqli_query($conn,$truyvan);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHTHUONGHIEU\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("MASP"=>$dong["MATHUONGHIEU"], "TENSP"=>$dong["TENTHUONGHIEU"], "HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dong["HINHTHUONGHIEU"])); 
			}
			echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		}
		echo "}";

	}

	function KiemTraDangNhap(){
		global $conn;
		if(isset($_POST["tendangnhap"]) || isset($_POST["matkhau"])){
			$tendangnhap = $_POST["tendangnhap"];
			$matkhau = $_POST["matkhau"];
		}

		$truyvan = "SELECT * FROM nguoidung WHERE TENDN='".$tendangnhap."' AND MATKHAU='".$matkhau."'";
		$ketqua = mysqli_query($conn, $truyvan); 
		$demdong = mysqli_num_rows($ketqua);
		if($demdong >=1){
			$tennguoidung = "";
			while ($dong = mysqli_fetch_array($ketqua)) {
				$tennguoidung = $dong["TENNGUOIDUNG"];
			}
			echo "{ketqua: true, tennguoidung :\"".$tennguoidung."\" }";
		} else{
			echo "{ketqua: false}";
		}
	}

	function DangKyThanhVien(){
		global $conn;
		if(isset($_POST["tennguoidung"]) || isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]) ||isset($_POST["maloainguoidung"]) || isset($_POST["emaildocquyen"])){

		$tennguoidung = $_POST["tennguoidung"];
		$tendangnhap = $_POST["tendangnhap"];
		$matkhau = $_POST["matkhau"];
		$maloainguoidung = $_POST["maloainguoidung"];
		$emaildocquyen = $_POST["emaildocquyen"];
		
		}
	

		$truyvan= "INSERT INTO nguoidung (TENNGUOIDUNG, TENDN, MATKHAU, MALOAINGUOIDUNG,EMAILDOCQUYEN) 
		VALUES ('".$tennguoidung."', '".$tendangnhap."', '".$matkhau."', '".$maloainguoidung."', '".$emaildocquyen."')";

		if (mysqli_query($conn, $truyvan)) {
			echo "{ketqua: true}";
		} else {
			echo "{ketqua: false}".mysqli_error($conn);
		}

		mysqli_close($conn);

	}

	function LayDanhSachMenu(){
		global $conn;

		if(isset($_POST["maloaicha"]))
		$maloaicha=$_POST["maloaicha"];

		$truyvan="SELECT * FROM loaisanpham WHERE MALOAI_CHA=".$maloaicha;
		$ketqua=mysqli_query($conn,$truyvan);
		$chuoijson=array();
		echo "{";
		echo "\"LOAISANPHAM\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("TENLOAISP"=>$dong["TENLOAISP"], "MALOAISP"=>$dong["MALOAISP"], "MALOAI_CHA"=>$dong["MALOAI_CHA"])); 
			}
			echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		}
		echo "}";

		mysqli_close($conn);


	}

?>