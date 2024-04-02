<script setup lang="ts">
import type { Student } from '../types/interfaces';
defineProps<{
    student: Student
}>()


</script>

<template>
    <div>
        <p>{{ student.name }} - {{ student.email }}</p>
    </div>
</template>