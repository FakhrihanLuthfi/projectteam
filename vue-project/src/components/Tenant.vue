<template>
  <div class="tenants-page pa-4">
    <v-container>
      <!-- Header dengan tombol tambah -->
      <div class="d-flex justify-space-between align-center mb-4">
        <h2>Daftar Penyewa</h2>
        <v-btn color="primary" prepend-icon="mdi-plus" @click="openAddDialog">
          Tambah Penyewa
        </v-btn>
      </div>

      <!-- Grid Penyewa -->
      <v-row>
        <v-col
          v-for="(tenant, index) in tenants"
          :key="index"
          cols="12" sm="6" md="3"
        >
          <v-card class="pa-3">
            <div class="d-flex justify-space-between align-center">
              <div>
                <div class="d-flex align-center mb-2">
                  <v-icon class="mr-2">mdi-account</v-icon>
                  <strong>{{ tenant.name || "-" }}</strong>
                </div>
                <div class="d-flex align-center">
                  <v-icon class="mr-2">mdi-door</v-icon>
                  <span>{{ tenant.room }}</span>
                </div>
              </div>
              <!-- Action Buttons -->
              <div>
                <v-btn icon size="small" @click="openEditDialog(index)">
                  <v-icon color="primary">mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon size="small" @click="confirmDelete(index)">
                  <v-icon color="red">mdi-delete</v-icon>
                </v-btn>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <!-- Dialog Tambah/Edit Penyewa -->
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="text-h6">
            {{ editIndex !== null ? "Edit Penyewa" : "Tambah Penyewa" }}
          </span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid">
            <v-text-field
              v-model="formTenant.name"
              label="Nama Penyewa"
              prepend-icon="mdi-account"
              required
            ></v-text-field>
            <v-text-field
              v-model="formTenant.room"
              label="Nomor Kamar"
              prepend-icon="mdi-door"
              required
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="dialog = false">Batal</v-btn>
          <v-btn color="primary" @click="saveTenant">
            {{ editIndex !== null ? "Update" : "Simpan" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- Dialog Konfirmasi Hapus -->
    <v-dialog v-model="deleteDialog" max-width="400px">
      <v-card>
        <v-card-title class="text-h6">Konfirmasi Hapus</v-card-title>
        <v-card-text>
          Apakah Anda yakin ingin menghapus <strong>{{ tenants[deleteIndex]?.name }}</strong> dari daftar penyewa?
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="deleteDialog = false">Batal</v-btn>
          <v-btn color="red" @click="deleteTenant">Hapus</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup>
import { ref } from "vue";

const dialog = ref(false);
const deleteDialog = ref(false);
const valid = ref(false);

const tenants = ref([
  { name: "Andi Saputra", room: "Kamar A1" },
  { name: "Budi Santoso", room: "Kamar A2" },
  { name: "Citra Dewi", room: "Kamar A3" },
  { name: "Diana Kusuma", room: "Kamar A4" },
  { name: "Natastya", room: "Kamar A5" },
]);

const formTenant = ref({ name: "", room: "" });
const editIndex = ref(null);
const deleteIndex = ref(null);

function openAddDialog() {
  formTenant.value = { name: "", room: "" };
  editIndex.value = null;
  dialog.value = true;
}

function openEditDialog(index) {
  formTenant.value = { ...tenants.value[index] };
  editIndex.value = index;
  dialog.value = true;
}

function saveTenant() {
  if (!formTenant.value.name || !formTenant.value.room) return;

  if (editIndex.value !== null) {
    // Update penyewa
    tenants.value[editIndex.value] = { ...formTenant.value };
  } else {
    // Tambah penyewa baru
    tenants.value.push({ ...formTenant.value });
  }

  dialog.value = false;
  formTenant.value = { name: "", room: "" };
  editIndex.value = null;
}

function confirmDelete(index) {
  deleteIndex.value = index;
  deleteDialog.value = true;
}

function deleteTenant() {
  if (deleteIndex.value !== null) {
    tenants.value.splice(deleteIndex.value, 1);
  }
  deleteDialog.value = false;
  deleteIndex.value = null;
}
</script>
