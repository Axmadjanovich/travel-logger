databaseChangeLog() {
    changeSet(id: 'create database', author: 'Abduvohid', runInTransaction: false) {
        sqlFile(path: 'changesets/V0_create_user.sql', splitStatements: false)
    }

    changeSet(id: 'create schema', author: 'Abduvohid') {
        sqlFile(path: 'changesets/V1_create_schema.sql', splitStatements: false)
    }

    changeSet(id: 'create travel_logs table', author: 'Abduvohid') {
        sqlFile(path: 'changesets/V2_travel_logs_table.sql', splitStatements: false)
    }

    changeSet(id: 'create users table', author: 'Abduvohid') {
        sqlFile(path: 'changesets/V3_users_table.sql', splitStatements: false)
    }

    changeSet(id: 'create vehicles table', author: 'Abduvohid') {
        sqlFile(path: 'changesets/V4_vehicles_table.sql', splitStatements: false)
    }

    changeSet(id: 'insert default values', author: 'Abduvohid') {
        sqlFile(path: 'changesets/V5_initial_data.sql', splitStatements: false)
    }
}