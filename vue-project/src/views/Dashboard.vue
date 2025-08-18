<template>
  <v-container fluid class="pl-0">
    <!-- Header -->
    <div class="d-flex justify-space-between align-center text-center mb-6">
      <h1 class="text-h4 font-weight-bold text-primary">Dashboard</h1>
      <v-btn color="primary" prepend-icon="mdi-plus" rounded="lg" elevation="1" class="pl-1">
        Tambah Penghuni
      </v-btn>
    </div>
    <v-row dense>
      <!-- Penghuni Aktif -->
      <v-col cols="12" md="4">
        <v-hover v-slot="{ isHovering, props }">
          <v-card
            v-bind="props"
            class="pa-4 transition-swing"
            color="grey-lighten-5"
            :elevation="isHovering ? 8 : 2"
          >
            <v-card-title class="d-flex align-center">
              <v-icon color="primary" class="mr-2">mdi-account-multiple</v-icon>
              <span class="font-weight-medium">Penghuni Aktif</span>
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold"> 45 Orang </v-card-text>
            <v-card-actions>
              <v-btn text small color="primary">Lihat Detail</v-btn>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-col>

      <!-- Pendapatan Bulan Ini -->
      <v-col cols="12" md="4">
        <v-hover v-slot="{ isHovering, props }">
          <v-card
            v-bind="props"
            class="pa-4 transition-swing"
            color="grey-lighten-5"
            :elevation="isHovering ? 8 : 2"
          >
            <v-card-title class="d-flex align-center">
              <v-icon color="success" class="mr-2">mdi-cash-multiple</v-icon>
              <span class="font-weight-medium">Pendapatan Bulan Ini</span>
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold text-success"> Rp 25.000.000 </v-card-text>
            <v-card-actions>
              <v-btn text small color="success">Lihat Laporan</v-btn>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-col>

      <!-- Kamar Tersedia -->
      <v-col cols="12" md="4">
        <v-hover v-slot="{ isHovering, props }">
          <v-card
            v-bind="props"
            class="pa-4 transition-swing"
            color="grey-lighten-5"
            :elevation="isHovering ? 8 : 2"
          >
            <v-card-title class="d-flex align-center">
              <v-icon color="warning" class="mr-2">mdi-door-open</v-icon>
              <span class="font-weight-medium">Kamar Tersedia</span>
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold text-warning-darken-2">
              8 Kamar
            </v-card-text>
            <v-card-actions>
              <v-btn text small color="warning">Lihat Kamar</v-btn>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-col>
    </v-row>

    <v-row dense class="mt-6">
      <!-- Grafik Pendapatan -->
      <v-col cols="12" md="6">
        <v-card elevation="2" color="grey-lighten-5">
          <v-card-title class="d-flex align-center">
            <v-icon color="primary" class="mr-2">mdi-chart-line</v-icon>
            Trend Pendapatan
          </v-card-title>
          <v-card-text>
            <p class="text-subtitle-2 text-grey">Pendapatan 6 bulan terakhir</p>
            <div
              class="bg-grey-lighten-4 rounded-lg"
              style="height: 200px; display: flex; align-items: center; justify-content: center"
            >
              <span class="text-grey-darken-2">[Grafik di sini]</span>
            </div>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Reminder Pembayaran -->
      <v-col cols="12" md="6">
        <v-card elevation="2" color="grey-lighten-5">
          <v-card-title class="d-flex align-center">
            <v-icon color="error" class="mr-2">mdi-bell-alert</v-icon>
            Pembayaran Jatuh Tempo
            <v-spacer></v-spacer>
            <v-chip color="error" size="small" text-color="white">3</v-chip>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <v-list density="compact">
              <v-list-item>
                <v-list-item-title>Rudi Santoso - Kamar A1</v-list-item-title>
                <v-progress-linear color="error" value="90" height="6" rounded />
              </v-list-item>
              <v-list-item>
                <v-list-item-title>Siti Aminah - Kamar B2</v-list-item-title>
                <v-progress-linear color="warning" value="60" height="6" rounded />
              </v-list-item>
              <v-list-item>
                <v-list-item-title>Budi Hartono - Kamar C3</v-list-item-title>
                <v-progress-linear color="primary" value="40" height="6" rounded />
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- Penghuni Baru -->
    <v-row dense class="mt-6">
      <v-col cols="12">
        <v-card elevation="2" color="grey-lighten-5">
          <v-card-title class="d-flex align-center">
            <v-icon color="primary" class="mr-2">mdi-account-plus</v-icon>
            Penghuni Baru Bulan Ini
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <v-list density="compact">
              <v-list-item>
                <v-list-item-avatar color="primary">
                  <v-icon dark>mdi-account</v-icon>
                </v-list-item-avatar>
                <v-list-item-title>Agus Pratama</v-list-item-title>
                <v-list-item-subtitle>Kamar D1</v-list-item-subtitle>
                <v-chip color="primary" size="small" text-color="white">Baru</v-chip>
              </v-list-item>
              <v-list-item>
                <v-list-item-avatar color="primary">
                  <v-icon dark>mdi-account</v-icon>
                </v-list-item-avatar>
                <v-list-item-title>Lina Marlina</v-list-item-title>
                <v-list-item-subtitle>Kamar E2</v-list-item-subtitle>
                <v-chip color="primary" size="small" text-color="white">Baru</v-chip>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup></script>

<style scoped>
.transition-swing {
  transition: all 0.3s ease-in-out;
}
</style>
