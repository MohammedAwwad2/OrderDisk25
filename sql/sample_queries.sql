-- Top 5 customers by total order amount in the last 30 days
-- Suggested indexes: orders(created_at), orders(customer_id, created_at)
SELECT c.id,
       c.name,
       c.email,
       COUNT(o.id)          AS order_count,
       COALESCE(SUM(o.amount), 0) AS total_amount
FROM customer c
JOIN orders o ON o.customer_id = c.id
WHERE o.created_at >= (NOW() AT TIME ZONE 'UTC') - INTERVAL '30 days'
GROUP BY c.id, c.name, c.email
ORDER BY total_amount DESC NULLS LAST
LIMIT 5;

-- Example EXPLAIN (run in psql):
-- EXPLAIN ANALYZE
-- SELECT ... (query above) ... ;
