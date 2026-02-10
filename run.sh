#!/bin/bash
# Script para ejecutar la aplicación Spring Boot

echo "==================================================="
echo "   Spring Boot MySQL CRUD Application"
echo "==================================================="
echo ""

# Verificar si Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java no está instalado."
    echo "Por favor, instale Java 17 o superior."
    exit 1
fi

echo "✅ Java detectado: $(java -version 2>&1 | head -n 1)"
echo ""

# Verificar si MySQL está corriendo
if command -v mysql &> /dev/null; then
    if mysql -u springuser -pThePassword -e "USE db_example;" 2>/dev/null; then
        echo "✅ Conexión a MySQL exitosa"
    else
        echo "⚠️  Advertencia: No se pudo conectar a MySQL"
        echo "   Asegúrese de que MySQL esté corriendo y configurado"
        echo "   Puede ejecutar: mysql -u root -p < setup-database.sql"
    fi
else
    echo "⚠️  MySQL no detectado. Asegúrese de que esté instalado y corriendo"
fi

echo ""
echo "Iniciando la aplicación..."
echo "==================================================="
echo ""

# Ejecutar la aplicación
mvn spring-boot:run

