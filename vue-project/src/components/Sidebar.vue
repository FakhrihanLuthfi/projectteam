<template>
  <v-app>
    <v-app-bar color="primary" dark app flat>
      <v-toolbar-title class="d-flex align-center">
      <v-icon class="me-2">mdi-home-city</v-icon>
      KostEase
    </v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      color="primary"
      dark
      :permanent="!isMobile"
      :temporary="isMobile"
      width="350"
      class="sidebar"
    >
      <!-- Profil Admin -->
      <div class="pa-4 d-flex align-center">
        <v-avatar color="white" size="40">
          <v-icon color="primary">mdi-account-circle</v-icon>
        </v-avatar>
        <div class="ml-3">
          <div class="font-weight-medium">Admin Kost</div>
          <div class="text-caption text-white-70">admin@kostku.com</div>
        </div>
      </div>

      <v-divider class="mb-2"></v-divider>

      <!-- Menu Items -->
      <v-list dense nav>
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

      <!-- Toggle Button -->
      <v-btn
        icon
        class="sidebar-toggle"
        @click="drawer = !drawer"
        :class="{ rotated: !drawer }"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
    </v-navigation-drawer>
  </v-app>
</template>

<script setup>
import { ref, computed } from "vue"
import { useDisplay } from "vuetify"

const drawer = ref(true)
const activeMenu = ref("home")

const { mdAndDown } = useDisplay()
const isMobile = computed(() => mdAndDown.value)

// Menu dengan icon yang lebih menarik
const menuItems = [
  { title: "Home", value: "home", icon: "mdi-home-circle-outline" },
  { title: "Daftar Kamar", value: "rooms", icon: "mdi-door-closed-lock" },
  { title: "Daftar Penyewa", value: "tenants", icon: "mdi-account-group-outline" },
  { title: "Laporan", value: "reports", icon: "mdi-file-chart-outline" },
  { title: "Bantuan", value: "help", icon: "mdi-help-circle-outline" },
   { title: "Pengaturan", value: "settings", icon: "mdi-cog-outline" },
]

function selectMenu(value) {
  activeMenu.value = value
  if (isMobile.value) drawer.value = false
}
</script>

<style scoped>
.sidebar {
  position: relative;
}

/* Toggle Button */
.sidebar-toggle {
  position: absolute;
  top: 50%;
  right: -20px; /* geser keluar sidebar */
  transform: translateY(-50%);
  background-color: #1976d2;
  color: white;
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
  transition: transform 0.3s ease;
}

/* Rotasi panah saat drawer tertutup */
.sidebar-toggle.rotated {
  transform: translateY(-50%) rotate(180deg);
}

.sidebar-toggle:hover {
  background-color: #115293;
  transform: translateY(-50%) scale(1.1);
}
</style>
