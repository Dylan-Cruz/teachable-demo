<script setup lang="ts">
import type { Course } from '../types/interfaces';
defineProps<{
    course: Course
}>()

</script>

<template>
    <div class="mt-5">
        <h3 class="mb-0" v-html="course.name"></h3>
        <p class="text-muted" v-html="course.heading"></p>

        <h5>Enrolled Students:</h5>
        <table class="table table-striped table-bordered my-table">
            <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in course.enrolledStudents" :key="user.id">
                    <td>{{ user.name }}</td>
                    <td>{{ user.email }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<style scoped>
.my-table {
    max-width: 900px;
    margin: 0 auto;
    word-break: break-word;
}
</style>