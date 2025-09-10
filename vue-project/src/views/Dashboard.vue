<template>
  <v-app>
    <!-- App Bar -->
    <v-app-bar app flat></v-app-bar>

    <v-main>
      <v-container fluid class="pa-0">
        <!-- Header & Action Button -->
        <div class="d-flex justify-space-between align-center mb-6">
          <h1 class="text-h4 font-weight-bold text-primary m-0">Home</h1>
          <v-btn color="primary" prepend-icon="mdi-plus" rounded="lg" elevation="1" class="px-4"
            @click="dialogTambah = true">
            Tambah Penghuni
          </v-btn>
        </div>

        <!-- Statistik Cards -->
        <v-row dense>
          <!-- Penghuni Aktif -->
          <v-col cols="12" md="4">
            <v-hover v-slot="{ isHovering, props }">
              <v-card v-bind="props" class="pa-4 transition-swing" color="grey-lighten-5"
                :elevation="isHovering ? 8 : 2">
                <v-card-title class="d-flex align-center">
                  <v-icon color="primary" class="mr-2">mdi-account-multiple</v-icon>
                  <span class="font-weight-medium">Penghuni Aktif</span>
                </v-card-title>
                <v-card-text class="text-h5 font-weight-bold">45 Orang</v-card-text>
                <v-card-actions>
                  <v-btn a href="/tenants" text small color="primary">Lihat Detail</v-btn>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-col>

          <!-- Pendapatan Bulan Ini -->
          <v-col cols="12" md="4">
            <v-hover v-slot="{ isHovering, props }">
              <v-card v-bind="props" class="pa-4 transition-swing" color="grey-lighten-5"
                :elevation="isHovering ? 8 : 2">
                <v-card-title class="d-flex align-center">
                  <v-icon color="success" class="mr-2">mdi-cash-multiple</v-icon>
                  <span class="font-weight-medium">Pendapatan Bulan Ini</span>
                </v-card-title>
                <v-card-text class="text-h5 font-weight-bold text-success">
                  Rp 25.000.000
                </v-card-text>
                <v-card-actions>
                  <v-btn a href="/reports" text small color="success">Lihat Laporan</v-btn>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-col>

          <!-- Kamar Tersedia -->
          <v-col cols="12" md="4">
            <v-hover v-slot="{ isHovering, props }">
              <v-card v-bind="props" class="pa-4 transition-swing" color="grey-lighten-5"
                :elevation="isHovering ? 8 : 2">
                <v-card-title class="d-flex align-center">
                  <v-icon color="warning" class="mr-2">mdi-door-open</v-icon>
                  <span class="font-weight-medium">Kamar Tersedia</span>
                </v-card-title>
                <v-card-text class="text-h5 font-weight-bold text-warning-darken-2">
                  8 Kamar
                </v-card-text>
                <v-card-actions>
                  <v-btn a href="/rooms" text small color="warning">Lihat Kamar</v-btn>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-col>
        </v-row>

        <!-- Grafik & Reminder -->
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
                <div class="bg-grey-lighten-4 rounded-lg pa-2" style="height: 200px;">
                  <LineChart />
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
                  <v-list-item>
                    <v-list-item-title>John Doe - Kamar C5</v-list-item-title>
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
    </v-main>

    <v-dialog v-model="dialogTambah" max-width="600px" persistent transition="dialog-bottom-transition">
      <v-card rounded="xl">
        <v-card-title class="text-h6 font-weight-bold">
          <v-icon color="success" class="mr-2">mdi-account-plus</v-icon>
          Tambah Penghuni Baru
        </v-card-title>
        <v-divider></v-divider>

        <v-card-text>
          <v-stepper v-model="step" flat>
            <!-- Header -->
            <v-stepper-header>
              <v-stepper-item :complete="step > '1'" value="1" title="Nama" />
              <v-divider></v-divider>
              <v-stepper-item :complete="step > '2'" value="2" title="Kamar" />
              <v-divider></v-divider>
              <v-stepper-item value="3" title="Status" />
            </v-stepper-header>

            <!-- Window -->
            <v-stepper-window>
              <!-- Nama -->
              <v-stepper-window-item value="1">
                <v-text-field v-model="form.nama" label="Nama Penghuni" prepend-icon="mdi-account" :error="!form.nama"
                  :error-messages="!form.nama ? 'Nama wajib diisi' : ''" />
              </v-stepper-window-item>

              <!-- Kamar -->
              <v-stepper-window-item value="2">
                <v-text-field v-model="form.kamar" label="Nomor Kamar" prepend-icon="mdi-door" :error="!form.kamar"
                  :error-messages="!form.kamar ? 'Nomor kamar wajib diisi' : ''" />
              </v-stepper-window-item>

              <!-- Status -->
              <v-stepper-window-item value="3">
                <v-select v-model="form.status" :items="['Aktif', 'Tidak Aktif']" label="Status Penghuni"
                  prepend-icon="mdi-check-circle" />
              </v-stepper-window-item>
            </v-stepper-window>
          </v-stepper>
        </v-card-text>

        <v-divider></v-divider>
        <v-card-actions class="d-flex justify-end">
          <v-btn variant="text" color="grey" @click="closeDialog">Batal</v-btn>
          <v-btn v-if="step !== '1'" variant="text" color="primary" @click="prevStep">
            Kembali
          </v-btn>
          <v-btn v-if="step !== '3'" color="primary" @click="nextStep"
            :disabled="(step === '1' && !form.nama) || (step === '2' && !form.kamar)">
            Lanjut
          </v-btn>
          <v-btn v-else color="success" prepend-icon="mdi-content-save" @click="simpanPenghuni">
            Simpan
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>


    <!-- SNACKBAR NOTIF -->
    <v-snackbar v-model="snackbar" color="success" timeout="3000" rounded="pill">
      <v-icon start>mdi-check-circle</v-icon>
      Penghuni berhasil ditambahkan!
    </v-snackbar>
  </v-app>
</template>

<script setup>
import { ref } from "vue";
import LineChart from "@/components/LineChart.vue"

const dialogTambah = ref(false);
const snackbar = ref(false);

const step = ref("1");

const form = ref({
  nama: "",
  kamar: "",
  status: "Aktif",
});

const prevStep = () => {
  step.value = (parseInt(step.value) - 1).toString();
};

const nextStep = () => {
  step.value = (parseInt(step.value) + 1).toString();
};

const closeDialog = () => {
  dialogTambah.value = false;
  step.value = "1";
  form.value = { nama: "", kamar: "", status: "Aktif" };
};

const simpanPenghuni = () => {
  console.log("Data penghuni baru:", form.value);
  snackbar.value = true;
  closeDialog();
};
</script>

<style scoped></style>
