<template>
  <div id="app">    
    <DarkMode />
    <FormComponent />
  </div>
</template>

<script>
import FormComponent from './components/FormComponent.vue';
import DarkMode from './components/DarkMode.vue';

export default {
  components: {
    FormComponent,
    DarkMode
  }
};
</script>

<style>
#app {
  font-family: Monospace, Lucida Console;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

/* styles.css o app.css */
:root {
  --background-color: #ffffff;
  --text-color: #000000;
}

.dark-theme {
  --background-color: #0e0e0e;
  --text-color: #ffffff; 
}

.dark-theme .form-card{
  background-color:  #181818;
}

.dark-theme .form-title {
  color: #ffffff;
}

body {
  background-color: var(--background-color);
  color: var(--text-color);
  transition: background-color 0.3s, color 0.3s;
}


</style>
