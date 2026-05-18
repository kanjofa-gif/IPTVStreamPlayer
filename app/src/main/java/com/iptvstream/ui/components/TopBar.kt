package com.iptvstream.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iptvstream.data.model.UserInfo
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
        TopBarIconButton(
            icon = Icons.Default.Settings,
            description = "الإعدادات",
            onClick = onSettingsClick,
            background = SurfaceVariant
        )

        Spacer(Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavTab.entries.reversed().forEach { tab ->
                TabItem(
                    tab = tab,
                    isSelected = tab == currentTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        TopBarIconButton(
            icon = Icons.Default.PlayArrow,
            description = "متابعة المشاهدة",
            onClick = onContinueWatchingClick,
            background = Primary,
            iconTint = Color.White
        )
    }
}

@Composable
fun TabItem(
    tab: NavTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val active = isFocused || isSelected

    Box(
        modifier = Modifier
            .scale(if (isFocused) 1.1f else 1f)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    isFocused -> Primary
                    isSelected -> Surface
                    else -> Color.Transparent
                }
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Color.White else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tab.label,
            color = when {
                isFocused -> Color.White
                isSelected -> TextPrimary
                else -> TextSecondary
            },
            fontSize = 15.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun TopBarIconButton(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit,
    background: Color,
    iconTint: Color = TextPrimary
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(44.dp)
            .scale(if (isFocused) 1.15f else 1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isFocused) Primary else background)
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Color.White else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = description, tint = if (isFocused) Color.White else iconTint)
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
                .clickable { }
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
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
                    SubscriptionRow("نسخة تجريبية:", if (isTrial) "نعم" else "لا")
                }

                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = com.iptvstream.ui.theme.Divider)
                Spacer(Modifier.height(8.dp))

                DrawerMenuItem(Icons.Default.Person, "الحساب", true, onAccount)
                DrawerMenuItem(Icons.Default.Refresh, "تحديث بيانات قائمة التشغيل", false, onRefresh)
                DrawerMenuItem(Icons.Default.GridView, "إدارة قوائم التشغيل", false, onManagePlaylists)
                DrawerMenuItem(Icons.Default.Tune, "إدارة الفئات", false, onManageCategories)
                DrawerMenuItem(Icons.Default.Settings, "الإعدادات", false, onSettings)
                DrawerMenuItem(Icons.Default.PhoneAndroid, "النسخة المحمولة", false, onMobileApp)
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
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(when {
                isFocused -> Primary
                isSelected -> SurfaceVariant
                else -> Color.Transparent
            })
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = if (isFocused) Color.White else TextPrimary, fontSize = 15.sp)
        Spacer(Modifier.width(12.dp))
        Icon(icon, contentDescription = label, tint = if (isFocused) Color.White else TextSecondary, modifier = Modifier.size(22.dp))
    }
}
