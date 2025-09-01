<template>
  <v-container fluid>
    <!-- Header -->
    <div class="d-flex justify-space-between align-center mb-4">
      <h1 class="text-h5 font-weight-bold">Laporan</h1>

      <div class="d-flex gap-2">
        <!-- Tombol Tambahkan Laporan -->
        <v-btn color="success" prepend-icon="mdi-plus" @click="openAddDialog">
          Tambahkan Laporan
        </v-btn>

        <!-- Tombol Export -->
        <v-menu>
          <template v-slot:activator="{ props }">
            <v-btn color="primary" prepend-icon="mdi-download" v-bind="props">
              Export
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="exportExcel">
              <v-list-item-title>Export ke Excel</v-list-item-title>
            </v-list-item>
            <v-list-item @click="exportPDF">
              <v-list-item-title>Export ke PDF</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </div>

    <!-- Filter -->
    <v-row class="mb-4">
      <v-col cols="12" md="3">
        <v-text-field
          v-model="search"
          label="Cari data"
          prepend-inner-icon="mdi-magnify"
          outlined
          dense
        />
      </v-col>

      <!-- Tanggal Mulai -->
      <v-col cols="12" md="3">
        <v-menu v-model="menuStart" :close-on-content-click="false" transition="scale-transition" offset-y>
          <template v-slot:activator="{ props }">
            <v-text-field v-model="startDate" label="Tanggal Mulai" readonly v-bind="props" outlined dense />
          </template>
          <v-date-picker v-model="startDate" @input="menuStart = false" />
        </v-menu>
      </v-col>

      <!-- Tanggal Akhir -->
      <v-col cols="12" md="3">
        <v-menu v-model="menuEnd" :close-on-content-click="false" transition="scale-transition" offset-y>
          <template v-slot:activator="{ props }">
            <v-text-field v-model="endDate" label="Tanggal Akhir" readonly v-bind="props" outlined dense />
          </template>
          <v-date-picker v-model="endDate" @input="menuEnd = false" />
        </v-menu>
      </v-col>
    </v-row>

    <!-- Tabel Laporan -->
    <v-data-table
      :headers="headers"
      :items="itemsWithTotal"
      :search="search"
      class="elevation-1"
    >
      <!-- Format angka -->
      <template v-slot:item.amount="{ item }">
        <span v-if="item.isTotal" class="font-weight-bold text-primary">
          Rp {{ item.amount.toLocaleString() }}
        </span>
        <span v-else>
          Rp {{ item.amount.toLocaleString() }}
        </span>
      </template>

      <!-- Bold baris Total -->
      <template v-slot:item.description="{ item }">
        <span v-if="item.isTotal" class="font-weight-bold text-primary">
          {{ item.description }}
        </span>
        <span v-else>
          {{ item.description }}
        </span>
      </template>

      <!-- Aksi Edit & Delete -->
      <template v-slot:item.actions="{ item, index }">
        <v-btn
          icon="mdi-pencil"
          color="warning"
          size="small"
          variant="text"
          @click="openEditDialog(item, index)"
        ></v-btn>
        <v-btn
          icon="mdi-delete"
          color="error"
          size="small"
          variant="text"
          @click="openDeleteDialog(index)"
        ></v-btn>
      </template>
    </v-data-table>

    <!-- Dialog Tambah Laporan -->
    <v-dialog v-model="dialogAdd" max-width="500">
      <v-card>
        <v-card-title>Tambah Laporan</v-card-title>
        <v-card-text>
          <v-text-field v-model="newData.date" label="Tanggal" type="date" outlined dense />
          <v-text-field v-model="newData.description" label="Deskripsi" outlined dense />
          <v-text-field v-model.number="newData.amount" label="Jumlah" type="number" outlined dense />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="dialogAdd = false">Batal</v-btn>
          <v-btn color="success" @click="saveAdd">Simpan</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- Dialog Edit Laporan -->
    <v-dialog v-model="dialogEdit" max-width="500">
      <v-card>
        <v-card-title>Edit Laporan</v-card-title>
        <v-card-text>
          <v-text-field v-model="editData.date" label="Tanggal" type="date" outlined dense />
          <v-text-field v-model="editData.description" label="Deskripsi" outlined dense />
          <v-text-field v-model.number="editData.amount" label="Jumlah" type="number" outlined dense />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="dialogEdit = false">Batal</v-btn>
          <v-btn color="primary" @click="saveEdit">Simpan</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- Dialog Konfirmasi Delete -->
    <v-dialog v-model="dialogDelete" max-width="400" persistent>
      <v-card>
        <v-card-title class="text-h6">Konfirmasi Hapus</v-card-title>
        <v-card-text>
          Apakah Anda yakin ingin menghapus laporan ini?
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn text @click="dialogDelete = false">Batal</v-btn>
          <v-btn color="error" @click="confirmDelete">Ya, Hapus</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, computed } from "vue"
import * as XLSX from "xlsx"
import { saveAs } from "file-saver"
import jsPDF from "jspdf"
import autoTable from "jspdf-autotable"

const search = ref("")
const startDate = ref("")
const endDate = ref("")
const menuStart = ref(false)
const menuEnd = ref(false)

const headers = [
  { text: "Tanggal", value: "date" },
  { text: "Deskripsi", value: "description" },
  { text: "Jumlah", value: "amount" },
  { text: "Aksi", value: "actions", sortable: false },
]

const items = ref([
  { date: "2025-08-01", description: "Pembayaran Kost A", amount: 1200000 },
  { date: "2025-08-05", description: "Pembayaran Kost B", amount: 1100000 },
  { date: "2025-08-10", description: "Pembayaran Kost C", amount: 1250000 },
])

/* === Dialog Tambah === */
const dialogAdd = ref(false)
const newData = ref({ date: "", description: "", amount: 0 })

function openAddDialog() {
  newData.value = { date: "", description: "", amount: 0 }
  dialogAdd.value = true
}

function saveAdd() {
  if (newData.value.date && newData.value.description && newData.value.amount) {
    items.value.push({ ...newData.value })
    dialogAdd.value = false
  }
}

/* === Dialog Edit === */
const dialogEdit = ref(false)
const editIndex = ref(null)
const editData = ref({ date: "", description: "", amount: 0 })

function openEditDialog(item, index) {
  editIndex.value = index
  editData.value = { ...item }
  dialogEdit.value = true
}

function saveEdit() {
  if (editIndex.value !== null) {
    items.value[editIndex.value] = { ...editData.value }
  }
  dialogEdit.value = false
}

/* === Dialog Delete === */
const dialogDelete = ref(false)
const deleteIndex = ref(null)

function openDeleteDialog(index) {
  deleteIndex.value = index
  dialogDelete.value = true
}

function confirmDelete() {
  if (deleteIndex.value !== null) {
    items.value.splice(deleteIndex.value, 1)
  }
  dialogDelete.value = false
}

/* === Filter === */
const filteredItems = computed(() => {
  return items.value.filter(item => {
    const itemDate = new Date(item.date)
    const start = startDate.value ? new Date(startDate.value) : null
    const end = endDate.value ? new Date(endDate.value) : null

    if (start && itemDate < start) return false
    if (end && itemDate > end) return false
    return true
  })
})

/* === Total === */
const totalAmount = computed(() =>
  filteredItems.value.reduce((sum, item) => sum + item.amount, 0)
)

const itemsWithTotal = computed(() => {
  return [
    ...filteredItems.value,
    {
      description: "TOTAL",
      amount: totalAmount.value,
      date: "",
      isTotal: true,
    },
  ]
})

/* === Export Excel === */
function exportExcel() {
  const exportData = filteredItems.value.map(item => ({
    Tanggal: item.date,
    Deskripsi: item.description,
    Jumlah: item.amount,
  }))
  exportData.push({
    Tanggal: "",
    Deskripsi: "TOTAL",
    Jumlah: totalAmount.value,
  })

  const worksheet = XLSX.utils.json_to_sheet(exportData)
  const workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, "Laporan")
  const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" })
  saveAs(new Blob([excelBuffer]), "laporan.xlsx")
}

/* === Export PDF === */
function exportPDF() {
  const doc = new jsPDF()
  doc.text("Laporan Kost", 14, 15)

  const body = filteredItems.value.map(item => [
    item.date,
    item.description,
    "Rp " + item.amount.toLocaleString(),
  ])
  body.push(["", "TOTAL", "Rp " + totalAmount.value.toLocaleString()])

  autoTable(doc, {
    head: [["Tanggal", "Deskripsi", "Jumlah"]],
    body,
    startY: 20,
  })

  doc.save("laporan.pdf")
}
</script>
