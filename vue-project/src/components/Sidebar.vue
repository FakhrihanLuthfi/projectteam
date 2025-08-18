<template>
  <v-app>
    <!-- App Bar -->
    <v-app-bar color="primary" dark app flat>
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-toolbar-title>KOSTKU</v-toolbar-title>
    </v-app-bar>

    
    <!-- Sidebar tanpa app supaya tidak ada space -->
    <v-navigation-drawer
      v-model="drawer"
      color="primary"
      dark
      :permanent="!isMobile"
      :temporary="isMobile"
      width="250"
    >
      <!-- User Info -->
      <div class="pa-4 d-flex align-center">
        <v-avatar color="white" size="40">
          <v-icon color="primary">mdi-account</v-icon>
        </v-avatar>
        <div class="ml-3">
          <div class="font-weight-medium">Admin Kost</div>
          <div class="text-caption text-white-70">admin@kostku.com</div>
        </div>
      </div>

      <v-divider class="mb-2"></v-divider>

      <!-- Menu List -->
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

const menuItems = [
  { title: "Home", value: "home", icon: "mdi-home" },
  { title: "Daftar Kamar", value: "rooms", icon: "mdi-door" },
  { title: "Daftar Penyewa", value: "tenants", icon: "mdi-account-multiple" },
  { title: "Pengaturan", value: "settings", icon: "mdi-cog" },
]

function selectMenu(value) {
  activeMenu.value = value
  if (isMobile.value) drawer.value = false
}
</script>

<style scoped>
.v-list-item {
  cursor: pointer;
  transition: background-color 0.2s;
}

.v-list-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
}
</style>
