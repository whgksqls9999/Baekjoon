SELECT
    E.EMP_NO,
    E.EMP_NAME,
    T.GRADE,
    CASE
        WHEN
            T.GRADE = 'S'
        THEN
            E.SAL * 0.2
        WHEN
            T.GRADE = 'A'
        THEN
            E.SAL * 0.15
        WHEN
            T.GRADE = 'B'
        THEN
            E.SAL * 0.1
        ELSE
            0
    END AS BONUS
FROM
    HR_EMPLOYEES E
JOIN
    (SELECT
        E.EMP_NO,
        CASE
            WHEN
                AVG(G.SCORE) >= 96
            THEN
                'S'
            WHEN
                AVG(G.SCORE) >= 90
            THEN
                'A'
            WHEN    
                AVG(G.SCORE) >= 80
            THEN
                'B'
            ELSE
                'C'
        END AS GRADE
    FROM
        HR_EMPLOYEES E
    JOIN
        HR_GRADE G
    ON
        E.EMP_NO = G.EMP_NO
    GROUP BY
        E.EMP_NO) AS T
ON
    E.EMP_NO = T.EMP_NO
ORDER BY
    E.EMP_NO