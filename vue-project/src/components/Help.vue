<template>
  <div class="help-page pa-4">
    <v-container>
      <h2 class="mb-4">Pusat Bantuan</h2>

      <!-- FAQ -->
      <v-expansion-panels>
        <v-expansion-panel
          v-for="(item, index) in faq"
          :key="index"
        >
          <v-expansion-panel-title>
            {{ item.question }}
          </v-expansion-panel-title>
          <v-expansion-panel-text>
            {{ item.answer }}
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>

      <!-- Kontak Admin -->
      <v-card class="mt-6 pa-4">
        <h3 class="mb-2">Butuh Bantuan Lebih Lanjut?</h3>
        <p>Hubungi Admin KostEase:</p>
        <v-list density="compact">
          <v-list-item prepend-icon="mdi-whatsapp">
            <v-list-item-title>+62 812-3456-7890</v-list-item-title>
          </v-list-item>
          <v-list-item prepend-icon="mdi-email-outline">
            <v-list-item-title>admin@kostku.com</v-list-item-title>
          </v-list-item>
          <v-list-item prepend-icon="mdi-phone">
            <v-list-item-title>(021) 1234-5678</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-card>
    </v-container>
  </div>
</template>

<script setup>
const faq = [
  { question: "Bagaimana cara menambahkan penyewa?", answer: "Masuk ke menu Daftar Penyewa, lalu klik tombol Tambah Penyewa." },
  { question: "Bagaimana cara melihat laporan pembayaran?", answer: "Buka menu Laporan, pilih periode, lalu download PDF/Excel." },
  { question: "Apakah data saya aman?", answer: "Ya, semua data disimpan secara lokal di browser dengan autentikasi login." },
];
</script>

<style scoped>
.help-page {
  max-width: 900px;
  margin: auto;
}
</style>
