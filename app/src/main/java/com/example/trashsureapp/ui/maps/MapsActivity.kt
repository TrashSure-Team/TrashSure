package com.example.trashsureapp.ui.maps

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.trashsureapp.databinding.ActivityMapsBinding
import com.example.trashsureapp.R
import com.google.android.gms.maps.model.LatLngBounds

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        // Add a marker in Sydney and move the camera
        val BankBlazent = LatLng(-7.801358485559444, 110.37841999133998)
        mMap.addMarker(MarkerOptions().position(BankBlazent).title("Bank Sampah Blazent"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BankBlazent))

        getMyLocation()

        val dicodingSpace = LatLng(-6.8957643, 107.6338462)
        mMap.addMarker(
            MarkerOptions()
                .position(dicodingSpace)
                .title("Dicoding Space")
                .snippet("Batik Kumeli No.50")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dicodingSpace, 15f))

        val BS1Space = LatLng(-7.293522499367822, 112.74747133005539)
        mMap.addMarker(
            MarkerOptions()
                .position(BS1Space)
                .title("Bank Sampah Kampung Dinoyo ")
                .snippet("Jl. Dinoyo Tengah No.32, RT.002/RW.03, Keputran, Kec. Tegalsari, Surabaya, Jawa Timur 60265")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS1Space, 15f))

        val BS2Space = LatLng( -7.1602700, 112.8524930)
        mMap.addMarker(
            MarkerOptions()
                .position(BS2Space)
                .title("SONG OSONG LOMBUNG")
                .snippet("DUSUN KAUMAN DESA KETETANG\n" +
                        "Kab. Bangkalan, Jawa Timu")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS2Space, 15f))

        val BS3Space = LatLng( -7.295225220402825, 112.80600785603697)
        mMap.addMarker(
            MarkerOptions()
                .position(BS3Space)
                .title("Bank Sampah Srikandi Keputih")
                .snippet("PR24+7JF, Jl. Keputih Tegal Timur Baru II, Keputih, Kec. Sukolilo, Surabaya, Jawa Timur 60111")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS3Space, 15f))

        val BS4Space = LatLng( -7.320288374938336, 112.71686273249416)
        mMap.addMarker(
            MarkerOptions()
                .position(BS4Space)
                .title("Bank Sampah Jambangan Pitu")
                .snippet("Blok A No., Jl. Jambangan Tama II No.23, Jambangan, Kec. Jambangan, Surabaya, Jawa Timur 60232")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS4Space, 15f))

        val BS5Space = LatLng( -7.320288374938336, 112.71686273249416)
        mMap.addMarker(
            MarkerOptions()
                .position(BS5Space)
                .title("Bank Sampah Jaya Makmur")
                .snippet("Jl. Mbah Sarah, Binangun, Wadungasih, Kec. Buduran, Kabupaten Sidoarjo, Jawa Timur 61252")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS5Space, 15f))


        val BS6Space = LatLng( -7.4457382602313675, 112.67198131312314)
        mMap.addMarker(
            MarkerOptions()
                .position(BS6Space)
                .title("Bank Sampah Bestari ( Bersih Lestari) Lebo")
                .snippet("Jl. Sulawesi, Lebo, Kec. Sidoarjo, Kabupaten Sidoarjo, Jawa Timur 61223")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS6Space, 15f))

        val BS7Space = LatLng( -7.992225210545411, 112.62040627479756)
        mMap.addMarker(
            MarkerOptions()
                .position(BS7Space)
                .title("Bank Sampah Sukun Berseri - Kertabumi")
                .snippet("Jl. Simpang Sukun No.26, Sukun, Kec. Sukun, Kota Malang, Jawa Timur 65147")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS7Space, 15f))

        val BS8Space = LatLng( -7.642133416214034, 111.52221464968488)
        mMap.addMarker(
            MarkerOptions()
                .position(BS8Space)
                .title("Bank Sampah Nurul Darussalam")
                .snippet("Taman, Kec. Taman, Kota Madiun, Jawa Timur 63131")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS8Space, 15f))

        val BS9Space = LatLng( -7.611893745802022, 111.5207655095588)
        mMap.addMarker(
            MarkerOptions()
                .position(BS9Space)
                .title("Bank Sampah Induk Madiun")
                .snippet("Jl. Candi Sewu No.26, Madiun Lor, Kec. Manguharjo, Kota Madiun, Jawa Timur 63122")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS9Space, 15f))

        val BS10Space = LatLng( -7.819971714356561, 110.35870814963569)
        mMap.addMarker(
            MarkerOptions()
                .position(BS10Space)
                .title("Bank Sampah Suryo Resik")
                .snippet("Mj 2/822, RT.44/RW.13, Suryodiningratan, Mantrijeron, Yogyakarta City, Special Region of Yogyakarta 55141")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS10Space, 15f))

        val BS11Space = LatLng( -6.97331767019344, 110.39812916809844)
        mMap.addMarker(
            MarkerOptions()
                .position(BS11Space)
                .title("Bank Sampah Resik Becik")
                .snippet("Jl. Cokrokembang No.11, Krobokan, Kec. Semarang Barat, Kota Semarang, Jawa Tengah 50141")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS11Space, 15f))

        val BS12Space = LatLng( -6.905461731768632, 107.6059193010646)
        mMap.addMarker(
            MarkerOptions()
                .position(BS12Space)
                .title("Bank Sampah Bakcis - Babakan Ciamis")
                .snippet("Jl. Babakan Ciamis, RT.02/RW.03, Babakan Ciamis, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40117")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS12Space, 15f))

        val BS13Space = LatLng( -6.56383801057309, 106.8041004650998)
        mMap.addMarker(
            MarkerOptions()
                .position(BS13Space)
                .title("Bank Sampah Barokah Bogor")
                .snippet(" Jl. Dadali Lb., RT.02 RW05/RW.05, Kec. Tanah Sereal, Kota Bogor, Jawa Barat 16161")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS13Space, 15f))

        val BS14Space = LatLng( -6.235420751259205, 106.82502915943344)
        mMap.addMarker(
            MarkerOptions()
                .position(BS14Space)
                .title("Bank Sampah Mekar Sari")
                .snippet(" Jl. Mampang Prapatan IV No.5 8, RT.8/RW.5, Mampang Prpt., Kec, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12790")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS14Space, 15f))

        val BS15Space = LatLng( -6.193867167527672, 106.61673545307139)
        mMap.addMarker(
            MarkerOptions()
                .position(BS15Space)
                .title("Bank Sampah Nusa Jaya")
                .snippet(" RJ38+4G7, Jl. Wijaya Kusuma Raya, RW.007, Nusa Jaya, Kec. Karawaci, Kota Tangerang, Banten 15116")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS15Space, 15f))

        val BS16Space = LatLng( -8.178384403173647, 113.64468969500678)
        mMap.addMarker(
            MarkerOptions()
                .position(BS16Space)
                .title("Bank Sampah Asri BMP")
                .snippet("BUMI MANGLI PERMAI BLOK IF-14, Krajan, Mangli, Kec. Kaliwates, Kabupaten Jember, Jawa Timur 68151")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS16Space, 15f))

        val BS18Space = LatLng( -10.189513348130193, 123.62024660256046)
        mMap.addMarker(
            MarkerOptions()
                .position(BS18Space)
                .title("Bank Sampah Mutiara Timor")
                .snippet("Jl. S.D. Laning, Maulafa, Kec. Maulafa, Kota Kupang, Nusa Tenggara Tim. 85141")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS18Space, 15f))

        val BS19Space = LatLng( -0.5612616826641703, 119.9451863576221)
        mMap.addMarker(
            MarkerOptions()
                .position(BS19Space)
                .title("Bank Sampah Navoe")
                .snippet("Taipa, Palu Utara, Palu City, Central Sulawesi 94141")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS19Space, 15f))

        val BS20Space = LatLng( -5.016828411874829, 119.46199459535242)
        mMap.addMarker(
            MarkerOptions()
                .position(BS20Space)
                .title("Bank sampah jolloro")
                .snippet(" Untia, Biringkanaya, Makassar City, South Sulawesi 90243")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS20Space, 15f))

        val BS21Space = LatLng( 1.483515709193101, 124.92661417391619)
        mMap.addMarker(
            MarkerOptions()
                .position(BS21Space)
                .title("Bank Sampah Wale Sejati")
                .snippet(" Kompleks Perum Wale Sejati Blok B14, Watutumou III, Kec. Kalawat, Kabupaten Minahasa Utara, Sulawesi Utara 95378")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS21Space, 15f))

        val BS22Space = LatLng( 5.567206537215818, 95.36708006572356)
        mMap.addMarker(
            MarkerOptions()
                .position(BS22Space)
                .title("USK Waste Banks")
                .snippet("Belakang OIA Unsyiah, Jl. Tgk. Syech Abdul Rauf, Kopelma Darussalam, Syiah Kuala, Banda Aceh City, Aceh 24415")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS22Space, 15f))

        val BS23Space = LatLng( 0.14968851699650831, 110.12695312500003)
        mMap.addMarker(
            MarkerOptions()
                .position(BS23Space)
                .title("Bank Sampah Lestari - Kertabumi")
                .snippet("Pedalaman, Tayan Hilir, Sanggau Regency, West Kalimantan 78564")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS23Space, 15f))

        val BS24Space = LatLng( -3.4308640867081386, 114.7856475892257)
        mMap.addMarker(
            MarkerOptions()
                .position(BS24Space)
                .title("Bank Sampah Benawa Raya Mandiri")
                .snippet("Jl. Musdalifah, Guntungmanggis, Kec. Landasan Ulin, Kota Banjar Baru, Kalimantan Selatan 70721")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS24Space, 15f))

        val BS25Space = LatLng( -1.1735889484767663, 116.9113564190419)
        mMap.addMarker(
            MarkerOptions()
                .position(BS25Space)
                .title("Bank Sampah Kota Hijau")
                .snippet("Sepinggan, Balikpapan Selatan, Balikpapan City, East Kalimantan 76116")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS25Space, 15f))

        val BS26Space = LatLng( -7.777547646128724, 110.37593090030427)
        mMap.addMarker(
            MarkerOptions()
                .position(BS26Space)
                .title("Kantor Bank Sampah Berseri Rukun Warga 2, Terban, Kecamatan Gondokusuman\n")
                .snippet("Jl. Cik Di Tiro No.12, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BS26Space, 15f))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }
    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_options, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.normal_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.satellite_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.terrain_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            R.id.hybrid_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
