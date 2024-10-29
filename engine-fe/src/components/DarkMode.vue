<template>
  <div>
    <el-switch
      v-model="isDarkMode"
      active-text="Dark Mode"
      inactive-text="Light Mode"
      @change="toggleTheme"
    />
    <!-- Contenuto della tua app -->
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      isDarkMode: false // Stato iniziale del tema (modalità chiara)
    };
  },
  mounted() {
    // Carica il tema dal localStorage, se esiste
    const savedTheme = localStorage.getItem('isDarkMode');
    if (savedTheme !== null) {
      this.isDarkMode = JSON.parse(savedTheme);
      this.applyTheme();
    }
  },
  methods: {
    toggleTheme() {
      this.applyTheme();
      // Salva la scelta nel localStorage
      localStorage.setItem('isDarkMode', JSON.stringify(this.isDarkMode));
    },
    applyTheme() {
      if (this.isDarkMode) {
        document.body.classList.add('dark-theme');
      } else {
        document.body.classList.remove('dark-theme');
      }
    }
  }
};
</script>

<style scoped>
/* Se preferisci, puoi aggiungere altri stili specifici qui */
</style>
