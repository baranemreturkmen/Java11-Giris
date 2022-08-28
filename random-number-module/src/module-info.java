import com.example.random.service.RandomNumberService;
import com.example.random.service.business.FastRandomNumberService;
import com.example.random.service.business.SecureNumberService;

module com.example.random {
    exports com.example.random.service;//Paketi açtım.
    provides RandomNumberService with FastRandomNumberService, SecureNumberService;
    //Interface'in implementasyonlarını açtım.

    /*export ile interfacelerin olduğu paketi açıyorum implementasyonların olduğu paketi değil.
    * Çünkü ileride implementasyonlar değişecektir. Ben aradaki bağlantıyı interfaceler ile
    * sağlamalıyım.*/
}