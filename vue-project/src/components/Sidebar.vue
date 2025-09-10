<template>
  <div>
    <!-- Sidebar -->
    <v-navigation-drawer
      v-model="drawer"
      color="primary"
      dark
      :permanent="!isMobile"
      :temporary="isMobile"
      :width="isMobile ? 220 : 280"
      class="sidebar"
    >
      <!-- Menu Items -->
      <v-list dense nav class="pt-2">
        <v-list-item
          v-for="item in menuItems"
          :key="item.value"
          :prepend-icon="item.icon"
          :title="item.title"
          :value="item.value"
          :active="activeMenu === item.value"
          @click="selectMenu(item.value)"
          class="rounded-lg mb-1"
        />
      </v-list>

      <!-- Profil Admin -->
  <div  
  class="admin-section pa-4 d-flex align-center mt-auto"
  style="cursor: pointer"
  @click="goToPengaturan"
>
  <v-avatar color="white" size="40">
    <v-icon color="primary">mdi-account-circle</v-icon>
  </v-avatar>
  <div class="ml-3">
    <div class="font-weight-medium">Admin Kost</div>
    <div class="text-caption text-white-70">admin@kostku.com</div>
  </div>
</div>
 
      <!-- Toggle button di DESKTOP (nempel di drawer) -->
      <v-btn
        v-if="!isMobile"
        icon
        class="sidebar-toggle-inside"
        @click="drawer = !drawer"
        :class="{ rotated: !drawer }"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
    </v-navigation-drawer>

    <!-- Toggle button di MOBILE (fixed di luar drawer) -->
    <v-btn
      v-if="isMobile"
      icon
      class="sidebar-toggle-outside"
      @click="drawer = !drawer"
      :class="{ rotated: !drawer }"
    >
      <v-icon>mdi-chevron-left</v-icon>
    </v-btn>
  </div>
</template>

<script setup>
import { ref, computed, watch } from "vue"
import { useDisplay } from "vuetify"
import { useRouter } from "vue-router"

function goToPengaturan() {
  router.push({ name: "settings" }) 
  // atau router.push("/pengaturan") kalau route kamu path-based
}

const drawer = ref(true)
const activeMenu = ref("home")

const { mdAndDown } = useDisplay()
const isMobile = computed(() => mdAndDown.value)
const router = useRouter()

// Atur default drawer: desktop terbuka, mobile tertutup
watch(isMobile, (val) => {
  drawer.value = !val
}, { immediate: true })

const menuItems = [
  { title: "Home", value: "Dashboard", icon: "mdi-home-circle-outline" },
  { title: "Daftar Kamar", value: "rooms", icon: "mdi-door-closed-lock" },
  { title: "Daftar Penyewa", value: "tenants", icon: "mdi-account-group-outline" },
  { title: "Laporan", value: "reports", icon: "mdi-file-chart-outline" },
  { title: "Keuangan", value: "finance", icon: "mdi-cash-multiple" },
  { title: "Tagihan & Pembayaran", value: "billing", icon: "mdi-receipt-text-outline" },
  { title: "Aset Kost", value: "assets", icon: "mdi-domain" },
  { title: "Jadwal Pembersihan", value: "cleaning", icon: "mdi-broom" },
  { title: "Layanan Laundry", value: "laundry", icon: "mdi-washing-machine" },
  { title: "Layanan Makanan", value: "food-service", icon: "mdi-food-outline" },
  { title: "Event & Komunitas", value: "events", icon: "mdi-calendar-account" },
  { title: "Chat & Komunikasi", value: "chat", icon: "mdi-chat-processing-outline" },
  { title: "Notifikasi", value: "notifications", icon: "mdi-bell-outline" },
  { title: "Monitoring CCTV", value: "cctv", icon: "mdi-cctv" },
  { title: "Pengaturan", value: "settings", icon: "mdi-cog-outline" },
  { title: "Bantuan", value: "help", icon: "mdi-help-circle-outline" },
];



function selectMenu(value) {
  activeMenu.value = value
  router.push({ name: value })   // ⬅️ ini bikin navigasi ke route sesuai "name"
  if (isMobile.value) drawer.value = false
}
</script>



<style scoped>
.sidebar {
  position: relative;
}

/* Profil Admin fix pojok kiri bawah */
.admin-section {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}

/* === TOGGLE BUTTON DESKTOP (inside drawer) === */
.sidebar-toggle-inside {
  position: absolute;
  top: 50%;
  right: -20px;
  transform: translateY(-50%);
  background-color: #1976d2;
  color: white;
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
  transition: transform 0.3s ease;
}
.sidebar-toggle-inside.rotated {
  transform: translateY(-50%) rotate(180deg);
}
.sidebar-toggle-inside:hover {
  background-color: #115293;
  transform: translateY(-50%) scale(1.1);
}

/* === TOGGLE BUTTON MOBILE (outside drawer, fixed) === */
.sidebar-toggle-outside {
  position: fixed;
  top: 50%;
  left: 0;
  transform: translate(-50%, -50%);
  background-color: #1976d2;
  color: white;
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
  transition: transform 0.3s ease;
  z-index: 3000;
}
.sidebar-toggle-outside.rotated {
  transform: translate(-50%, -50%) rotate(180deg);
}
.sidebar-toggle-outside:hover {
  background-color: #115293;
  transform: translate(-50%, -50%) scale(1.1);
}
</style>
