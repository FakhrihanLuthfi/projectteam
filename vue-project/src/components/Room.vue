<template>
  <div class="p-4">
    <h2 class="text-h5 mb-4">Daftar Kamar</h2>
    <v-row>
      <v-col
        v-for="room in rooms"
        :key="room.id"
        cols="12"
        sm="6"
        md="4"
        lg="3"
      >
        <v-card class="rounded-lg elevation-2">
          <v-card-title>{{ room.name }}</v-card-title>
          <v-card-subtitle>
            Status:
            <span :class="room.occupied ? 'text-red' : 'text-green'">
              {{ room.occupied ? "Terisi" : "Kosong" }}
            </span>
          </v-card-subtitle>
          <v-card-text>
            Harga: Rp {{ room.price.toLocaleString() }}
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script setup>
import { ref } from "vue"

const rooms = ref([
  { id: 1, name: "Kamar A1", occupied: false, price: 800000 },
  { id: 2, name: "Kamar A2", occupied: true, price: 900000 },
  { id: 3, name: "Kamar A3", occupied: false, price: 750000 },
  { id: 4, name: "Kamar A4", occupied: false, price: 850000 },
  { id: 5, name: "Kamar B1", occupied: true, price: 1300000 },
  { id: 6, name: "Kamar B2", occupied: true, price: 1600000 },
  { id: 7, name: "Kamar CB3", occupied: true, price: 1200000 },
  { id: 8, name: "Kamar C1", occupied: false, price: 1500000 },
  { id: 9, name: "Kamar C2", occupied: true, price: 1000000 },
  { id: 10, name: "Kamar C3", occupied: true, price: 900000 },
  { id: 11, name: "Kamar C4", occupied: true, price: 1100000 },
  { id: 12, name: "Kamar C5", occupied: false, price: 1700000 },
])
</script>
