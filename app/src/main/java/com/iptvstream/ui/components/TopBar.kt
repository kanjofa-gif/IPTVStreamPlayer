package com.iptvstream.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iptvstream.data.model.UserInfo
import com.iptvstream.ui.Screen
import com.iptvstream.ui.theme.*

enum class NavTab(val label: String, val icon: ImageVector) {
    HOME("الرئيسية", Icons.Default.Home),
    LIVE("البث المباشر", Icons.Default.LiveTv),
    MOVIES("الأفلام", Icons.Default.Movie),
    SERIES("المسلسلات", Icons.Default.Tv),
    SEARCH("بحث", Icons.Default.Search),
    EPG("EPG", Icons.Default.CalendarMonth)
}

@Composable
fun IPTVTopBar(
    currentTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    onSettingsClick: () -> Unit,
    onContinueWatchingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Background)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Settings gear icon
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .size(44.dp)
                .background(SurfaceVariant, RoundedCornerShape(8.dp))
        ) {
            Icon(Icons.Default.Settings, contentDescription = "الإعدادات", tint = TextPrimary)
        }

        Spacer(Modifier.weight(1f))

        // Nav tabs - right to left (Arabic)
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavTab.entries.reversed().forEach { tab ->
                val isSelected = tab == currentTab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) Surface else Color.Transparent)
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab.label,
                        color = if (isSelected) TextPrimary else TextSecondary,
                        fontSize = 15.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(Modifier.weight(1f))

        // Continue watching button
        IconButton(
            onClick = onContinueWatchingClick,
            modifier = Modifier
                .size(44.dp)
                .background(Primary, RoundedCornerShape(8.dp))
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "متابعة المشاهدة", tint = Color.White)
        }
    }
}

@Composable
fun SettingsDrawer(
    isVisible: Boolean,
    userInfo: UserInfo?,
    expDate: String,
    maxConnections: String,
    isTrial: Boolean,
    onDismiss: () -> Unit,
    onAccount: () -> Unit,
    onRefresh: () -> Unit,
    onManagePlaylists: () -> Unit,
    onManageCategories: () -> Unit,
    onSettings: () -> Unit,
    onWhatsNew: () -> Unit,
    onMobileApp: () -> Unit,
    onNotes: () -> Unit
) {
    if (!isVisible) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .clickable { onDismiss() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .width(340.dp)
                .align(Alignment.CenterStart)
                .background(Surface)
                .clickable { /* consume */ }
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // Subscription info header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceVariant, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    SubscriptionRow("حالة اشتراك IPTV:", if (userInfo?.status == "Active") "Active ✓" else userInfo?.status ?: "-", AccentGreen)
                    SubscriptionRow("الحد الأقصى للاتصالات:", maxConnections)
                    SubscriptionRow("صالح حتى:", expDate)
                    SubscriptionRow("الحد الأقصى للاتصالات:", maxConnections)
                    SubscriptionRow("نسخة تجريبية:", if (isTrial) "نعم" else "لا")
                }

                Spacer(Modifier.height(12.dp))
                Divider(color = Divider)
                Spacer(Modifier.height(8.dp))

                DrawerMenuItem(Icons.Default.Person, "الحساب", true, onAccount)
                DrawerMenuItem(Icons.Default.Refresh, "تحديث بيانات قائمة التشغيل", false, onRefresh)
                DrawerMenuItem(Icons.Default.GridView, "إدارة قوائم التشغيل", false, onManagePlaylists)
                DrawerMenuItem(Icons.Default.Tune, "إدارة الفئات", false, onManageCategories)
                DrawerMenuItem(Icons.Default.Settings, "الإعدادات", false, onSettings)
                DrawerMenuItem(Icons.Default.Star, "ما الجديد", false, onWhatsNew)
                DrawerMenuItem(Icons.Default.PhoneAndroid, "النسخة المحمولة", false, onMobileApp)
                DrawerMenuItem(Icons.Default.Comment, "ملاحظات", false, onNotes)
            }
        }
    }
}

@Composable
fun SubscriptionRow(label: String, value: String, valueColor: Color = TextPrimary) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(value, color = valueColor, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.width(8.dp))
        Text(label, color = TextSecondary, fontSize = 13.sp)
    }
}

@Composable
fun DrawerMenuItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) SurfaceVariant else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = TextPrimary, fontSize = 15.sp)
        Spacer(Modifier.width(12.dp))
        Icon(icon, contentDescription = label, tint = TextSecondary, modifier = Modifier.size(22.dp))
    }
}
