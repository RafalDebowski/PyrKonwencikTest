import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pyrkonwenciknew.domain.model.GuestDomain
import com.example.pyrkonwenciknew.ui.compose.guestList.SearchBarView
import com.example.pyrkonwenciknew.ui.compose.guestDetails.UserImage

@Composable
fun GuestListView(
    guestsList: List<GuestDomain>,
    onClick: (item: GuestDomain) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredList = guestsList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        SearchBarView(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        filteredList.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onClick(it) }
            ) {
                UserImage(it.imageUrl, 50.dp)
                Text(
                    text = it.name,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp)
                )
            }
        }
    }
}
