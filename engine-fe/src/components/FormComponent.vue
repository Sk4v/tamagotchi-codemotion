<template>
  <div class="card-container">    
    <el-card class="form-card" shadow="hover">
      <img alt="Vue logo" src="../assets/logo.png" width="40" height="auto">
      <h2 class="form-title">Suitability Engine</h2>
      <el-form :model="formData" ref="form">
        <el-form-item label="Risk Profile" prop="riskProfile" label-position="top">
          <el-select v-model="formData.riskProfile" placeholder="Select Risk Profile" @change="fetchData">
            <el-option v-for="item in riskProfiles" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Time Horizon" prop="timeHorizon" label-position="top">
          <el-select v-model="formData.timeHorizon" placeholder="Select Time Horizon">
            <el-option v-for="item in timeHorizons" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Region" prop="region" label-position="top">
          <el-select v-model="formData.region" placeholder="Select Region">
            <el-option v-for="item in regions" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm" :disabled="isFormIncomplete" style="width:100%; height: 50px; font-weight: bold; font-size: 1em;">Confirm</el-button>
        </el-form-item>
      </el-form>

      <div v-if="message" style="margin-top: 20px;">
        <el-alert :title="message" :type="alertType" show-icon />
      </div>

      <el-table :data="results" v-if="results.length" style="margin-top: 20px;" height="400px">
        <el-table-column prop="name" label="Name" />
        <el-table-column prop="isin" label="ISIN" />
        <el-table-column prop="asset" label="Asset" />
        <el-table-column prop="region" label="Region" />
        <el-table-column prop="volatility" label="Volatility" />
      </el-table>
    </el-card>
  </div>
</template>
    

<script>
import axios from 'axios';

// const API_BASE_URL = process.env.API_BASE_URL; 
// console.log("API: " + API_BASE_URL)

export default {
  data() {
    return {
      formData: {
        riskProfile: '',
        timeHorizon: '',
        region: '',
      },
      regions: [],
      riskProfiles: [],
      timeHorizons: [],
      results: [],
      message: '',
      alertType: 'success'
    };
  },
  created() {
    this.fetchRegions();
    this.fetchRiskProfiles();
    this.fetchTimeHorizons();
  },
  computed: {
    isFormIncomplete() {
      return !(this.formData.riskProfile && this.formData.timeHorizon && this.formData.region);
    }
  },
  methods: {
    fetchRegions() {
      axios.get(`/domain/regions`)
        .then(response => {
          if (response.data.status === 'OK') {
            this.regions = response.data.data;
          }
        })
        .catch(error => {
          console.error('There was an error fetching regions:', error);
        });
    },
    fetchRiskProfiles() {
      axios.get(`/domain/riskProfiles`)
        .then(response => {
          if (response.data.status === 'OK') {
            this.riskProfiles = response.data.data;
          }
        })
        .catch(error => {
          console.error('There was an error fetching risk profiles:', error);
        });
    },
    fetchTimeHorizons() {
      axios.get(`/domain/timeHorizon`)
        .then(response => {
          if (response.data.status === 'OK') {
            this.timeHorizons = response.data.data;
          }
        })
        .catch(error => {
          console.error('There was an error fetching time horizons:', error);
        });
    },
    submitForm() {
      axios.post(`http://localhost:8080/suitability/check`, this.formData)
        .then(response => {
          if (response.data.status === 'OK') {
            this.results = response.data.data;
            this.message = `${response.data.data.length} products found`;
          } else {
            this.message = 'No products found';
          }
        })
        .catch(error => {
          console.error('There was an error fetching the suitability results:', error);
          this.message = 'Error fetching results';
          this.alertType = 'error'; 
        });
    },
  },
};

</script>

<style scoped>
.card-container {
  display: flex;
  justify-content: center;
  align-items: center;  
  margin-top:20px;
}

.form-card {
  width: 50%; /* Set a fixed width for the card */
  border-radius: 12px; /* Rounded corners */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Light shadow */
  padding: 20px; /* Add padding inside the card */
}

.form-title {
  text-align: center;
  margin-bottom: 20px; /* Space below the title */
}

.el-form-item {
  margin-bottom: 20px; /* Space between form items */
}

.custom-form-item .el-form-item__label {
  display: block;  /* Fa sì che l'etichetta occupi tutta la larghezza */
  margin-bottom: 5px;  /* Aggiunge un margine sotto l'etichetta */
}

.el-select {
  width: 100%; /* Set all selects to full width */
}

</style>
  
  