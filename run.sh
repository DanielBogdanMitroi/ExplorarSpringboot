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

# Verificar que la contraseña de MySQL esté configurada
if [ -z "$MYSQL_PASSWORD" ]; then
    echo "⚠️  Advertencia: Variable MYSQL_PASSWORD no configurada"
    echo "   Debe configurar la contraseña antes de ejecutar:"
    echo "   export MYSQL_PASSWORD=su_contraseña"
    echo ""
    read -p "¿Desea continuar de todas formas? (s/n): " -n 1 -r
    echo ""
    if [[ ! $REPLY =~ ^[Ss]$ ]]; then
        exit 1
    fi
fi

# Verificar si MySQL está corriendo (sin exponer la contraseña)
if command -v mysql &> /dev/null; then
    if [ ! -z "$MYSQL_PASSWORD" ]; then
        # Usar un archivo temporal para las credenciales (más seguro que -p en línea de comandos)
        TEMP_CNF=$(mktemp)
        cat > "$TEMP_CNF" << EOF
[client]
user=${MYSQL_USER:-springuser}
password=$MYSQL_PASSWORD
EOF
        chmod 600 "$TEMP_CNF"
        
        if mysql --defaults-extra-file="$TEMP_CNF" -e "USE db_example;" 2>/dev/null; then
            echo "✅ Conexión a MySQL exitosa"
        else
            echo "⚠️  Advertencia: No se pudo conectar a MySQL"
            echo "   Asegúrese de que MySQL esté corriendo y configurado"
            echo "   Puede ejecutar: mysql -u root -p < setup-database.sql"
        fi
        
        # Eliminar el archivo temporal de credenciales
        rm -f "$TEMP_CNF"
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

