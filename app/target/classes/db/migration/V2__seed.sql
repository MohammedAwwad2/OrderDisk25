-- Seed data
INSERT INTO customer (name, email) VALUES
  ('Alice Example', 'alice@example.com'),
  ('Bob Example', 'bob@example.com')
ON CONFLICT (email) DO NOTHING;

-- A few orders (spread across days)
INSERT INTO orders (customer_id, amount, currency, status, created_at) VALUES
  ((SELECT id FROM customer WHERE email='alice@example.com'), 49.99, 'USD', 'PAID', NOW() - INTERVAL '0 day'),
  ((SELECT id FROM customer WHERE email='alice@example.com'), 19.95, 'USD', 'PENDING', NOW() - INTERVAL '0 day'),
  ((SELECT id FROM customer WHERE email='bob@example.com'),   100.00, 'USD', 'PAID', NOW() - INTERVAL '1 day');
