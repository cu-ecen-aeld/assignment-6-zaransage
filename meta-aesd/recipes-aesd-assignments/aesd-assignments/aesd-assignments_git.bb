SUMMARY = "AESD Socket Server"
DESCRIPTION = "Yocto recipe for building and installing the aesdsocket application."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-zaransage.git;protocol=ssh;branch=master"
SRCREV = "fe528c44c7fb84a1cf94fe1cbb0eb55c7c252933"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/server"

inherit autotools

# Add the aesdsocket binary to the package
FILES:${PN} += "${bindir}/aesdsocket"

# Custom linker flags for pthread and librt
TARGET_LDFLAGS += "-pthread -lrt"

do_configure() {
    # Skip configuration as it's not needed for this simple application
    :
}

do_compile() {
    # Use Yocto's build helper to run make
    oe_runmake
}

do_install() {
    # Create the installation directory for the binaries
    install -d ${D}${bindir}
    # Install the compiled aesdsocket binary
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket
}

