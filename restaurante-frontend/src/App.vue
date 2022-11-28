<template>
  <nav class="navbar navbar-expand-lg bg-info navbar-text">
    <div class="container-fluid">
      <router-link to="/" class="navbar-brand" href="#">Restaurante.<span
          class="text-md-start fs-3 fw-bold">SHONATALE</span></router-link>
      <ul class="d-flex navbar navbar-expand-lg navbar-text nav" role="search">
        <li class="nav-item">
          <router-link to="/" class="nav-link fw-semibold fs-4" href="#">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link fw-semibold fs-4" href="#">Login</router-link>
        </li>
      </ul>      
    </div>
  </nav>

  <div class="container-fluid mt-3">
    <router-view />
  </div>
</template>

