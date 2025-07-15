-- ============================================
-- FARM MANAGEMENT SYSTEM - MIGRATION V1
-- Purpose: Normalised schema with audit fields, comments, initial dummy data
-- Dependencies: Authentication schema already defined
-- ============================================

-- ========================
-- 1. SUPPLIER TABLE
-- ========================
CREATE TABLE IF NOT EXISTS supplier (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100),
    phone VARCHAR(50),
    email VARCHAR(100),
    address TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy suppliers
INSERT INTO supplier (name, contact_person, phone, email, address, created_by)
VALUES
    ('AgroWorld Ltd', 'Jane Moyo', '263771234567', 'jane@agroworld.com', '123 Main Rd, Harare', 'system'),
    ('GreenGrow Co', 'Tom Ncube', '263772345678', 'tom@greengrow.co.zw', '45 Fields St, Mutare', 'system');

-- ========================
-- 2. INPUT ITEM TABLE
-- ========================
CREATE TABLE IF NOT EXISTS input_item (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL, -- e.g. CONSUMABLE, NON_CONSUMABLE
    unit VARCHAR(20) NOT NULL, -- kg, liters, etc
    buffer_level NUMERIC(10,2) DEFAULT 0,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy input items
INSERT INTO input_item (name, type, unit, buffer_level, created_by)
VALUES
    ('Maize Seed', 'CONSUMABLE', 'kg', 50, 'system'),
    ('Diesel', 'CONSUMABLE', 'liters', 100, 'system'),
    ('Tractor', 'NON_CONSUMABLE', 'unit', 0, 'system');

-- ========================
-- 3. INPUT STOCK TABLE
-- ========================
CREATE TABLE IF NOT EXISTS input_stock (
                                           id SERIAL PRIMARY KEY,
                                           input_item_id INT NOT NULL REFERENCES input_item(id),
    quantity NUMERIC(10,2) NOT NULL,
    source VARCHAR(100),
    acquisition_date DATE NOT NULL,
    cost_per_unit NUMERIC(12,2),
    supplier_id INT REFERENCES supplier(id) ON DELETE SET NULL,
    season_id INT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy stock
INSERT INTO input_stock (input_item_id, quantity, source, acquisition_date, cost_per_unit, supplier_id, created_by)
VALUES
    ((SELECT id FROM input_item WHERE name = 'Maize Seed'), 500, 'Local Supplier', CURRENT_DATE, 2.50, (SELECT id FROM supplier WHERE name = 'AgroWorld Ltd'), 'system'),
    ((SELECT id FROM input_item WHERE name = 'Diesel'), 1000, 'Fuel Depot', CURRENT_DATE, 1.45, (SELECT id FROM supplier WHERE name = 'GreenGrow Co'), 'system');

-- ========================
-- 4. CROP TABLE
-- ========================
CREATE TABLE IF NOT EXISTS crop (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
    default_yield_per_ha NUMERIC(10,2),
    duration_days INT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy crops
INSERT INTO crop (name, default_yield_per_ha, duration_days, created_by)
VALUES
    ('Maize', 3.5, 120, 'system'),
    ('Cotton', 1.2, 150, 'system');

-- ========================
-- 5. SEASON TABLE
-- ========================
CREATE TABLE IF NOT EXISTS season (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy season
INSERT INTO season (name, start_date, end_date, created_by)
VALUES
    ('2024/2025 Rainy Season', '2024-10-01', '2025-03-31', 'system');

-- ========================
-- 6. COST CENTER TABLE
-- ========================
CREATE TABLE IF NOT EXISTS cost_center (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL,
    size_hectares NUMERIC(10,2),
    location TEXT,
    soil_type VARCHAR(100),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy field
INSERT INTO cost_center (name, size_hectares, location, soil_type, created_by)
VALUES
    ('Field A', 25.0, 'West Block', 'Loam', 'system');

-- ========================
-- 7. FIELD CROP ALLOCATION
-- ========================
CREATE TABLE IF NOT EXISTS field_crop (
                                          id SERIAL PRIMARY KEY,
                                          cost_center_id INT NOT NULL REFERENCES cost_center(id),
    crop_id INT NOT NULL REFERENCES crop(id),
    season_id INT NOT NULL REFERENCES season(id),
    hectares_allocated NUMERIC(10,2),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy field crop allocation
INSERT INTO field_crop (cost_center_id, crop_id, season_id, hectares_allocated, created_by)
VALUES
    ((SELECT id FROM cost_center WHERE name = 'Field A'), (SELECT id FROM crop WHERE name = 'Maize'), (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'), 13.0, 'system'),
    ((SELECT id FROM cost_center WHERE name = 'Field A'), (SELECT id FROM crop WHERE name = 'Cotton'), (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'), 12.0, 'system');

-- ========================
-- 8. CROP INPUT REFERENCE
-- ========================
CREATE TABLE IF NOT EXISTS crop_input_reference (
                                                    id SERIAL PRIMARY KEY,
                                                    crop_id INT NOT NULL REFERENCES crop(id),
    input_item_id INT NOT NULL REFERENCES input_item(id),
    quantity_per_hectare NUMERIC(10,2),
    unit_cost_estimate NUMERIC(10,2),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy crop input references
INSERT INTO crop_input_reference (crop_id, input_item_id, quantity_per_hectare, unit_cost_estimate, created_by)
VALUES
    ((SELECT id FROM crop WHERE name = 'Maize'), (SELECT id FROM input_item WHERE name = 'Maize Seed'), 10, 2.5, 'system'),
    ((SELECT id FROM crop WHERE name = 'Maize'), (SELECT id FROM input_item WHERE name = 'Diesel'), 30, 1.4, 'system');

-- ========================
-- 9. CROP REVENUE REFERENCE
-- ========================
CREATE TABLE IF NOT EXISTS crop_revenue_reference (
                                                      id SERIAL PRIMARY KEY,
                                                      crop_id INT NOT NULL REFERENCES crop(id),
    yield_per_hectare NUMERIC(10,2),
    unit_price_estimate NUMERIC(10,2),
    revenue_unit VARCHAR(20),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy crop revenue reference
INSERT INTO crop_revenue_reference (crop_id, yield_per_hectare, unit_price_estimate, revenue_unit, created_by)
VALUES
    ((SELECT id FROM crop WHERE name = 'Maize'), 3.5, 300, 'tons', 'system');

-- ========================
-- 10. LABOR GRADE TABLE
-- ========================
CREATE TABLE IF NOT EXISTS labor_grade (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL,
    description TEXT,
    wage_type VARCHAR(50) NOT NULL, -- DAILY or MONTHLY
    daily_rate NUMERIC(10,2),
    monthly_salary NUMERIC(12,2),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy labor grades
INSERT INTO labor_grade (name, description, wage_type, daily_rate, monthly_salary, created_by)
VALUES
    ('General Worker', 'Unskilled casual labor', 'DAILY', 10.00, NULL, 'system'),
    ('Tractor Driver', 'Skilled operator', 'DAILY', 20.00, NULL, 'system'),
    ('Supervisor', 'Oversees operations', 'MONTHLY', NULL, 300.00, 'system');

-- ========================
-- 11. LABOR TABLE
-- ========================
CREATE TABLE IF NOT EXISTS labor (
                                     id SERIAL PRIMARY KEY,
                                     full_name VARCHAR(100) NOT NULL,
    national_id VARCHAR(50),
    contact_number VARCHAR(50),
    employment_type VARCHAR(50) NOT NULL, -- PERMANENT or CASUAL
    grade_id INT REFERENCES labor_grade(id),
    date_joined DATE,
    is_active BOOLEAN DEFAULT TRUE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy labor entries
INSERT INTO labor (full_name, employment_type, grade_id, is_active, created_by)
VALUES
    ('Joseph Banda', 'CASUAL', (SELECT id FROM labor_grade WHERE name = 'General Worker'), TRUE, 'system'),
    ('Peter Dube', 'CASUAL', (SELECT id FROM labor_grade WHERE name = 'Tractor Driver'), TRUE, 'system'),
    ('Samantha Chipo', 'PERMANENT', (SELECT id FROM labor_grade WHERE name = 'Supervisor'), TRUE, 'system');

-- ========================
-- 12. LABOR ASSIGNMENT TABLE
-- ========================
CREATE TABLE IF NOT EXISTS labor_assignment (
                                                id SERIAL PRIMARY KEY,
                                                labor_id INT NOT NULL REFERENCES labor(id),
    activity_id INT,
    cost_center_id INT REFERENCES cost_center(id),
    season_id INT REFERENCES season(id),
    days_worked NUMERIC(4,2),
    task_description TEXT,
    date_assigned DATE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy labor assignments
INSERT INTO labor_assignment (labor_id, cost_center_id, season_id, days_worked, date_assigned, created_by)
VALUES
    ((SELECT id FROM labor WHERE full_name = 'Joseph Banda'), (SELECT id FROM cost_center WHERE name = 'Field A'), (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'), 1.0, CURRENT_DATE, 'manager'),
    ((SELECT id FROM labor WHERE full_name = 'Peter Dube'), (SELECT id FROM cost_center WHERE name = 'Field A'), (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'), 1.0, CURRENT_DATE, 'manager');

-- ========================
-- 13. ACTIVITY TABLE
-- ========================
CREATE TABLE IF NOT EXISTS activity (
                                        id SERIAL PRIMARY KEY,
                                        type VARCHAR(50) NOT NULL, -- e.g., PLOUGHING, PLANTING, etc
    date DATE NOT NULL,
    cost_center_id INT REFERENCES cost_center(id),
    season_id INT REFERENCES season(id),
    crop_id INT REFERENCES crop(id),
    description TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy activities
INSERT INTO activity (type, date, cost_center_id, season_id, crop_id, description, created_by)
VALUES
    ('PLOUGHING', CURRENT_DATE,
     (SELECT id FROM cost_center WHERE name = 'Field A'),
     (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'),
     (SELECT id FROM crop WHERE name = 'Maize'),
     'Initial ploughing for maize', 'system');

-- ============================
-- 14. ACTIVITY INPUT TABLE
-- ============================
CREATE TABLE IF NOT EXISTS activity_input (
                                              id SERIAL PRIMARY KEY,
                                              activity_id INT REFERENCES activity(id),
    input_item_id INT REFERENCES input_item(id),
    quantity_used NUMERIC(10,2),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy activity inputs
INSERT INTO activity_input (activity_id, input_item_id, quantity_used, created_by)
VALUES
    ((SELECT id FROM activity WHERE type = 'PLOUGHING'), (SELECT id FROM input_item WHERE name = 'Diesel'), 60.0, 'system');

-- ========================
-- 15. HARVEST TABLE
-- ========================
CREATE TABLE IF NOT EXISTS harvest (
                                       id SERIAL PRIMARY KEY,
                                       cost_center_id INT REFERENCES cost_center(id),
    crop_id INT REFERENCES crop(id),
    season_id INT REFERENCES season(id),
    harvest_date DATE NOT NULL,
    yield_qty NUMERIC(12,2),
    unit VARCHAR(20),
    notes TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy harvest
INSERT INTO harvest (cost_center_id, crop_id, season_id, harvest_date, yield_qty, unit, notes, created_by)
VALUES
    ((SELECT id FROM cost_center WHERE name = 'Field A'),
     (SELECT id FROM crop WHERE name = 'Maize'),
     (SELECT id FROM season WHERE name = '2024/2025 Rainy Season'),
     CURRENT_DATE, 45.00, 'tons', 'Good harvest overall', 'system');

-- ========================
-- 16. SALE TABLE
-- ========================
CREATE TABLE IF NOT EXISTS sale (
                                    id SERIAL PRIMARY KEY,
                                    harvest_id INT REFERENCES harvest(id),
    buyer VARCHAR(100),
    quantity_sold NUMERIC(12,2),
    unit_price NUMERIC(10,2),
    total_amount NUMERIC(12,2),
    sale_date DATE NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_on TIMESTAMP,
    updated_by VARCHAR(255)
    );

-- Dummy sale
INSERT INTO sale (harvest_id, buyer, quantity_sold, unit_price, total_amount, sale_date, created_by)
VALUES
    ((SELECT id FROM harvest WHERE yield_qty = 45.00), 'GMB Depot', 45.00, 320.00, 14400.00, CURRENT_DATE, 'system');
