import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import java.util.*


fun Base64BitMap(base64: String): Bitmap? {
    val pureBase64Encoded = base64.substringAfter(",")
    val decodedBytes = Base64.getDecoder().decode(pureBase64Encoded)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}
