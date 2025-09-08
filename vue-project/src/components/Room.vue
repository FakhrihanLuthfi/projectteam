<template>
  <div class="p-4">
    <h2 class="text-h5 mb-4">Daftar Kamar</h2>

    <!-- Daftar Kamar -->
    <v-row>
      <v-col v-for="room in rooms" :key="room.id" cols="12" sm="6" md="4" lg="3">
        <v-card class="rounded-lg elevation-2 hoverable" @click="openDetail(room)">
          <v-img :src="room.thumbnail" height="150" cover class="rounded-t-lg"></v-img>
          <v-card-title>{{ room.name }}</v-card-title>
          <v-card-subtitle>
            Status:
            <span :class="room.occupied ? 'text-red' : 'text-green'">
              {{ room.occupied ? 'Terisi' : 'Kosong' }}
            </span>
          </v-card-subtitle>
          <v-card-text> Harga: Rp {{ room.price.toLocaleString() }} </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- Modal Detail -->
    <v-dialog v-model="dialog" position="center"  width="1000px" max-height="6000px" max-width="700px">
      <v-card v-if="selectedRoom" class="rounded-xl">
        <v-img :src="selectedRoom.foto" height="650" cover class="rounded-t-xl"></v-img>
        <v-card-title>{{ selectedRoom.name }}</v-card-title>
        <v-card-subtitle>
          Status:
          <span :class="selectedRoom.occupied ? 'text-red' : 'text-green'">
            {{ selectedRoom.occupied ? 'Terisi' : 'Kosong' }}
          </span>
        </v-card-subtitle>
        <v-card-text>
          <p><strong>Harga:</strong> Rp {{ selectedRoom.price.toLocaleString() }}</p>
          <p><strong>Luas:</strong> {{ selectedRoom.luas }} m²</p>
          <p><strong>Fasilitas:</strong> {{ selectedRoom.fasilitas }}</p>
          <p><strong>Deskripsi:</strong> {{ selectedRoom.deskripsi }}</p>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false">Tutup</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
const dialog = ref(false)
const selectedRoom = ref(null)
const rooms = ref([
  {
    id: 1,
    name: 'Kamar A1',
    occupied: false,
    price: 800000,
    luas: 20,
    fasilitas: 'AC, WiFi, Kamar Mandi Dalam',
    deskripsi: 'Kamar nyaman dengan pencahayaan alami.',
    thumbnail: 'https://picsum.photos/300/200?random=1',
    foto: 'https://picsum.photos/600/400?random=1',
  },
  {
    id: 2,
    name: 'Kamar A2',
    occupied: true,
    price: 900000,
    luas: 18,
    fasilitas: 'Kipas Angin, WiFi',
    deskripsi: 'Kamar ekonomis cocok untuk mahasiswa.',
    thumbnail: 'https://picsum.photos/300/200?random=2',
    foto: 'https://picsum.photos/600/400?random=2',
  },
  {
    id: 3,
    name: 'Kamar A3',
    occupied: false,
    price: 750000,
    luas: 16,
    fasilitas: 'WiFi',
    deskripsi: 'Kamar sederhana dengan harga terjangkau.',
    thumbnail: 'https://picsum.photos/300/200?random=3',
    foto: 'https://picsum.photos/600/400?random=3',
  },
])

function openDetail(room) {
  selectedRoom.value = room
  dialog.value = true
}
</script>
